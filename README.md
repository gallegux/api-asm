# asm-api
Tool to generate an API from ASM files

You can comment files, variables, constants, functions and macros.
All comments start with **;;** and all of them must be joined, even with the code. When the program finds a a blank line, ot forgets the previous comment lines.

## Comments format

#### Files
Comments for files must start at the beginning of the file, at first line and you you can have several lines

#### Variables
Variables must start with a dot and finish with a colon.

_;; Description of variable_

*.*variable_name*:*

#### Constants
Constants are defined with *EQU*

_;; description of constant_

ConstantName *EQU* value

#### Functions and macros
Functions and macros can have more kind of lines. You can use what you need.
You can add comments for inputs and outputs and a line for modified registers.
***;; in*** and ***;; out*** must be in a single line.
You can use *:* instead of *-* following inputs, outputs and modified registers.

Functions must start with a colon, and macros must be followed by *macro*

_;; description_

_;; another line for description_

_;; in_

_;; A - datum #1_
_;; BC - datum #2_
_;; out_
_;; DE - result #1_
_;; HL - result #2_
_;; mod - A BC IX_
my_function:

_;; description_
_;; another line for description_
_;; in_
_;; argument1 - datum #1_
_;; argument2 - datum #2_
_;; out_
_;; DE - result #1_
_;; HL - result #2_
_;; mod - A BC IX_
macro MyMacro argument1, argument2

If the inputs and outputs are registers they will be as uppercase in the result API.
The registers in Z80 are A, B, C, D, E, H, L, IXH, IXL, IYH, YHL, BC, DE, HL, IX, IY, SP.
