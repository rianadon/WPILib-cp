# Data sent from driver station to robot
# References:
# * https://github.com/gluxon/node-driverstation15/wiki/2015-Protocol
# * https://github.com/FRC-Utilities/LibDS/blob/master/src/protocols/frc_2015.c
# * https://github.com/jcreigh/FRCDriverStation/wiki

meta:
  id: ds2robot
  endian: be
seq:
  - id: seq_num
    type: u2 # Encoded as MSB
  - id: protocol_version
    contents: [0x01]

    # The FMS control code (1 byte)
  - id: emergency_stopped # |= 0x80
    type: b1
  - type: b3 # Filler
  - id: fms_attached # |= 0x08
    type: b1
  - id: robot_enabled # |= 0x04
    type: b1
  - id: control_mode
    type: b2
    enum: control_mode

    # Request flags
  - id: request_code
    type: u1
    enum: request_code

    # Alliance station
  - id: alliance_station
    type: u1
    enum: station_code

    # Timezone data is optional, takes up 14 bytes, and
    # begins with [0x0b, 0x0f]. Due to the limits of
    # this format I can't parse this, but it really
    # isn't *that* important, right?

    # Joystick data
  - id: joysticks
    type: joystick
    repeat: eos

types:
  joystick:
    seq:
      - id: size
        type: u1
      - id: header
        contents: [0x0c]

        # Joystick axes
      - id: num_axes
        type: u1
      - id: axes
        type: u1
        repeat: expr
        repeat-expr: num_axes

        # Joystick buttons
      - id: num_buttons
        type: u1
      - id: buttons # Note that these go in reverse, so the
                    # button at index i will be toggled if
                    # data & 2^i > 0 (note int is encoded
                    # as MSB)
        type: b1
        repeat: expr
        repeat-expr: num_buttons
      - type: b1 # Filler for buttons
        repeat: expr
        repeat-expr: 16-num_buttons

        # Joystick hats
      - id: num_hats
        type: u1
      - id: hats
        type: u2
        repeat: expr
        repeat-expr: num_hats

enums:
  control_mode:
    0: teleop # |= 0x00
    1: test # |= 0x01
    2: auton # |= 0x02
  request_code:
    0x00: unconnected
    0x04: restart_code
    0x08: reboot
    0x80: normal
  station_code:
    0: red1
    1: red2
    2: red3
    3: blue1
    4: blue2
    5: blue3
