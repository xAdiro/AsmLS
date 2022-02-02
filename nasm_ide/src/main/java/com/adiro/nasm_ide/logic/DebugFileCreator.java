package com.adiro.nasm_ide.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.*;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.regex.Pattern;

public final class DebugFileCreator {
		
		public static void createDebugFile(String sourceFilePath) {
			var outputContent = "";
			var debugFilePath = getFileDirectory(sourceFilePath) + "/runDebug.asm";
			
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(sourceFilePath));
				
				
				
				String line = reader.readLine();
				int i = 0;
				while(line != null) {
					
					if(!line.isBlank()) {
						
						String regexLoop = "loop.*";
						var loopPattern = Pattern.compile(regexLoop);
						var matcher = loopPattern.matcher(line.trim());
						if(matcher.matches()) {
							
							String label = line.trim().substring(line.trim().indexOf("loop") + 4);
							
							
							line = "dec cx\n"
									+ "cmp cx, 0\n"
									+ "jnz "
									+ label + "\n";
						}
						
						
						outputContent += 
								  line	+ "\n"
								+ ";<DEBUG>\n"
								+ MessageFormat.format("mov	[dane], byte {0}", (i >> 8)) + "\n"
								+ MessageFormat.format("mov	[dane+1], byte {0}", (i & 0x00FF)) + "\n"
								+ "call 	zapLinie\n"	
								+ ";</DEBUG>\n";
						i++;
					}
					else {
						outputContent += line + "\n";
					}
					line = reader.readLine();
					
					
				}
				
				outputContent += "\n%include 'debug.asm'";
				
				reader.close();
			}
			catch(IOException e) {
				System.out.print("[FileCreator ERROR] couldn't find: " + sourceFilePath);
				e.printStackTrace();
			}
			
			
			try {
				
				var file = new File(debugFilePath);
				file.createNewFile();
				
				var writer = new BufferedWriter(new FileWriter(debugFilePath));
				writer.write(outputContent);
				writer.close();
			} catch (IOException e) {
				System.out.print("[FileCreator ERROR] couldn't write to: " + debugFilePath);
				e.printStackTrace();
			}
			
			System.out.println("[INFO] Generated debug file: " + debugFilePath);
		}
		
			
			
		
		private static String getFileDirectory(String filePath) {
			File sourceFile = new File(filePath);
			return sourceFile.getParent();
		}
}
