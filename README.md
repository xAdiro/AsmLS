# AsmLS
Assembly Life Saver is a very simple (primitive one might say) debugger for 16-bit nasm programs, with GPRs and flags preview.
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

### Final notes
It should be noted that both 'Edit' and 'Settings' sections in top menu bar are dummy buttons and shouldn't be expected to do anything. Potentially there might be added functionality to them in future
