package com.adiro.asmls.utilities;

import java.text.MessageFormat;
import java.util.regex.Pattern;

public class CodeSupplier {
    public static class Bat {
        final public static String RUNNABLE = "cd debug\n"
                + "a:\\nasm\\nasm -o program.com -f bin runDebug.asm\n"
                + "del *.log\n"
                + "program.com\n"
                + "cd ..";
    }
    public static class Asm{
        public static String getDebugLine(String line, int lineNumber){
            var outputLine = fixLoop(line);

            if(isMemoryLine(outputLine)){
                outputLine += "\t;MEMORY\n";
            }
            else{
                outputLine += "\n"
                        + ";<DEBUG>\n"
                        + getLineNumberForAsm(lineNumber)
                        + "call 	zapLinie\n"
                        + ";</DEBUG>\n";
            }

            return outputLine;
        }

        final public static String LIBRARY_LINE = "\n%include 'debug.asm'";

        private static String getLineNumberForAsm(int lineNumber){
            return MessageFormat.format("mov [dane], byte {0}", (lineNumber >> 8)) + "\n"
                    + MessageFormat.format("mov	[dane+1], byte {0}", (lineNumber & 0x00FF)) + "\n";
        }

        private static String fixLoop(String line) {
            if(isLoopLine(line)) {
                String label = loopLineToLabel(line);
                line = "dec cx\n"
                        + "cmp cx, 0\n"
                        + "jnz "
                        + label + "\n";
            }
            return line;
        }

        private static boolean isLoopLine(String line){
            String regexLoop = "loop.*";
            var loopPattern = Pattern.compile(regexLoop);
            var matcher = loopPattern.matcher(line.trim());

            return matcher.matches();
        }

        private static boolean isMemoryLine(String line) {
            String regexMemoryLine =".*[\t, ]+(db|dw|dd|dq|dt)[\t, ]+.*";
            var memoryLinePattern = Pattern.compile(regexMemoryLine);
            var matcher = memoryLinePattern.matcher(line);

            return matcher.matches();
        }

        private static String loopLineToLabel(String loopLine){
            return loopLine.trim().substring(loopLine.trim().indexOf("loop") + 4);
        }
    }
}
