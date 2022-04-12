package com.adiro.asmls.logic;

import com.adiro.asmls.gui.content.RegistersView;

public class RegistersManager {
    RegistersView registers;

    public RegistersManager(RegistersView registers){
        this.registers = registers;
    }

    public void set(byte[] debugInfo) {
        registers.setAx(readRegister(debugInfo, 1));
        registers.setBx(readRegister(debugInfo, 2));
        registers.setCx(readRegister(debugInfo, 3));
        registers.setDx(readRegister(debugInfo, 4));
        registers.setFlags(readRegister(debugInfo, 9));
    }

    public int readLineNumber(byte[] debugInfo){
        return readRegister(debugInfo, 0);
    }

    private int readRegister(byte[] debugInfo, int byteNumber) {
        int value = (int) (ConvertToUnsignedValue(debugInfo[2*byteNumber]) << 8);
        value += ConvertToUnsignedValue(debugInfo[2*byteNumber+1]);
        return value;
    }

    private int ConvertToUnsignedValue(byte signedByte) {
        final int bias = 256;

        if(signedByte < 0) {
            return (int)signedByte + bias;
        }
        return (int)signedByte;
    }
}
