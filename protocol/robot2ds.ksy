# Data sent from robot to driver station
# References:
# * https://github.com/gluxon/node-driverstation15/wiki/2015-Protocol
# * https://github.com/FRC-Utilities/LibDS/blob/master/src/protocols/frc_2015.c
# * https://github.com/jcreigh/FRCDriverStation/wiki

meta:
  id: robot2ds
  endian: be
seq:
  - id: seq_num
    type: u2 # Encoded as MSB
  - id: protocol_version
    contents: [0x01]

    # The FMS control code with extra data (1 byte)
  - id: emergency_stopped # |= 0x80
    type: b1
  - type: b2 # Filler
  - id: brownout
    type: b1
  - id: code_state
    type: b1
    enum: code_state
  - id: robot_enabled # |= 0x04
    type: b1
  - id: control_mode
    type: b2
    enum: control_mode

    # Extra control byte
  - type: b2 # Filler
  - id: has_robot_code
    type: b1
  - type: b5 # Filler

    # Battery data
  - id: battery_b1
    type: u1
  - id: battery_b2
    type: u1

    # Request date flag;
    # 0x00 for false, 0x01 for true
  - id: request_date
    type: u1

    # TODO: fill this out
  - id: extra_status
    size-eos: true

instances:
  battery_voltage:
    value: battery_b1 + battery_b2/255.0

enums:
  control_mode:
    0: teleop # |= 0x00
    1: test # |= 0x01
    2: auton # |= 0x02
  code_state:
    0: running
    1: initializing
