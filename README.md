# AsmLS

### Table of Contents:
- [General info](#general-info)
- [Technologies](#technologies-used)
- [Features](#features)
- [Before using](#before-using)
- [How to use](#how-to-use)
- [How it works](#how-it-works)
- [Final notes](#final-notes)

## General info
AsmLS (which states for Assembly Life Saver) is a very simple (primitive one might say) debugger for 16-bit nasm programs, with GPRs and flags preview.
After picking your assembly file it will generate runDebu.bat next to it that you can run in your dos in order to debug your program later. 

It was created as a consequence of many of my college classmates struggles with debugging assembly code. There are obviously better tools for such a task but my motivation was to challenge myself both in assembly and java programming. Also its ease of use allows students to use it right away in a convenient portable form rather than configuring one of modern IDEs.

<img src="https://raw.githubusercontent.com/xAdiro/ASMLS/main/AsmLS-breakpoints.png" align="left"></img>
ㅤ
### Technologies used
- Java 11
- JavaFX 11
- NASM assembly

### Features
- Line by line code analysis
- GPRs and flags register preview
- Finding program exit point
- Analysis with breakpoints

### Before using
- AsmLS assumes you have a nasm compiler at `a:/nasm/nasm.exe` in your dos environment, so make sure it is that way or otherwise change that location in runDebu.bat script.
- Application requires java >=11 to run 

## How to use
1. Go to File -> Open new File, and choose your assembly file from dos
2. Next to it ASMLS generated runDebu.bat which is your program but it will also provide debug info for AsmLS
3. Run runDebu.but in your dos environment and after it ends proceed to AsmLS window
4. You can now use arrows to navigate through lines in AsmLS
5. If you make changes to your assembly file make sure to click refresh button to generate new runDebu.bat with updated code

You can also add breakpoints clicking next to line number and go between set breakpoints with specified buttons.

## How it works
The application neither runs assembly code nor emulates/predicts its behaviour but rather modifies original code to work the same way but with additon of saving needed debug info which consists of:
  - GPRs values
  - Flags register value
  - Number of executed line 

It does it as a result of quite simple but effective method which is generating new file but with new code after each line of original code in order to save required data. For that purpose I wrote [assembly function](https://github.com/xAdiro/ASMLS/blob/main/src/main/resources/com/adiro/asmls/files/debug.asm) which save mentioned data to binary .LOG files, that can be later read by AsmLS. Thanks to ease of use of the function `zapLinie` each line of new debug code looks as follows:
```asm
<line from original code>
mov [dane], byte <older 8 bits of line number>
mov [dane+1], byte <younger 8 bits of line number>
call zapLinie
```
To make it more legible and easier to debug in future debug code has been wrapped in pseudo tag to indicate its purpose, so the final code looks as follows:
```asm
<line from original code>
;<DEBUG>
mov [dane], byte <older 8 bits of line number>
mov [dane+1], byte <younger 8 bits of line number>
call zapLinie
;</DEBUG>
```
As an example line (assume it's 27th line of our code):
```asm
xchg AX, BX
```
will be converted to:
```asm
xchg AX, BX
;<DEBUG>
mov [dane], byte 0
mov [dane+1], byte 27
call zapLinie
;</DEBUG>
```
It's important to note that as a consequence of such process `loop` instruction's range from original code can be easily exceeded. To solve that problem loop instructions have to be converted to dec, cmp jump so that line:
```asm
loop YOUR_LABEL
```
becomes:
```asm
dec CX
cmp CX, 0
jnz YOUR_LABEL
```
Finally to not disturb structure of data in memory, lines that reserves memory space are left as they are just with addition of comment to indicate they have been read by AsmLS, so that line:
```asm
variable db "Hello world!"
```
becomes:
```asm
variable db "Hello world!"  ;MEMORY
```

## Final notes
My solution definitely isn't the fastest one but it does its job and also allows to further expansion of usability to stack and variables preview in addition to their coprocessor counterparts.
At the end it should be noted that both 'Edit' and 'Settings' sections in top menu bar are dummy buttons and shouldn't be expected to do anything. Potentially there might be added functionality to them in future
