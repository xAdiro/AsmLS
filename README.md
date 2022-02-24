# ASMLS
Is a very primitive nasm assembly debugger currently with GPRs and flags preview.
After picking your assembly file it will generate debug.bat next to it that you can run in your dos.

It assumes you have a nasm compiler at a:/nasm/nasm.exe (otherwise you have to change it in runDebu.bat)

## Quick instruction
1. Go to File -> Open new File, and choose your assembly file from dos
2. Next to it ASMLS generated runDebu.bat which is your program but it will provide debug info for ASMLS
3. Run runDebu.but and after it ends proceed to ASMLS window
4. You can now use arrows to navigate through lines
5. If you make changes to your assembly file click refresh button to generate new runDebu.bat with newest code

### Requires java 11 or higher

<img src="https://raw.githubusercontent.com/xAdiro/ASMLS/main/AsmLS-breakpoints.png" align="left"></img>
