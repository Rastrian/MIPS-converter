package mips.converter.invoke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import mips.converter.decode.DecodeOperands;
import mips.converter.decode.Opcode;
import mips.converter.helpers.InstructionHelpers;
import mips.converter.io.ReadFile;
import mips.converter.io.WriteFile;

/**
 * MIPSDecoder
 */
public class MIPSDecoder {
    private static FileWriter getCleanFileWriter(String path) {
        File f = new File(path);
    }

    public static void run(String inputPath, String outputPath) throws Exception {
        ReadFile readFile = new ReadFile();
        File file = new File(outputPath);

        if (!file.createNewFile()) {
            FileWriter tempFileWriter = new FileWriter(file);
            tempFileWriter.write("");
            tempFileWriter.close();
        }

        FileWriter writeFile = new FileWriter(file, true);

        try {
            List<String> commands = readFile.getCommands(inputPath);

            for (int i = 0; i < commands.size(); i++) {
                String decoded = processInstruction(commands.get(i));
                writeFile.append(decoded + "\n");
                writeFile.flush();
            }

            writeFile.close();
        } catch (Exception e) {
            throw e;
        }
    }

    public static String processInstruction(String instruction) throws Exception {
        try {
            String[] bits = instruction.replace("$", "").split(" ");
            String opcode = bits[0];
            String instructionType = InstructionHelpers.getRegisterType(opcode);
            String[] operands = instruction.replace(opcode, "").replace(" ", "").split(",");

            return getBinary(instructionType, opcode, operands);
        } catch (Exception e) {
            throw e;
        }
    }

    public static String getBinary(String instructionType, String opCode, String[] operands) {
        return Opcode.MAP.get(opCode) + DecodeOperands.decode(instructionType, opCode, operands);
    }
}