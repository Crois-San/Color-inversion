import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Scanner;

class Code {
    public static native void blue(int[] row, int[] a, int count);
    static {System.loadLibrary("Code");}
    public static void main(String[] arguments) {
		Scanner in = new Scanner(System.in);
		File f;
		String filename;
        do{
			System.out.println("Enter picture filename");
			filename = in.nextLine();
			f = new File(filename);
		}while(!f.exists());
		
		Scanner in2 = new Scanner(System.in);
		int b = 0;
		System.out.println("Enter the number");
		while(b < 1){
		if (in2.hasNextInt()) {
                b = in2.nextInt();
            }else {
                in2.next();
                System.out.println("Entered is not a number ");
            }
		}
		int[] a = new int[4];
		b=(b*0x10000+b*0x100+b)*0x100;
		for(int i = 0; i < 4; i++){
			a[i] = b;
		}
		
        try (DataInputStream file = new DataInputStream(new FileInputStream(filename))) {
            final String suffix = "_result";
            int width, height;
            byte[] header = new byte[0x36];
            file.read(header);
            width = byteArrayToInt(header, 0x12);
            height = byteArrayToInt(header, 0x16);
            int[][] pixmap = new int[height][width];
            for (int i = 0; i < height; i++) for (int j = 0; j < width; j++) pixmap[i][j] = file.readInt();
            file.close();
            filename = filename.substring(0x0, filename.length() - 4) + suffix + ".bmp";
            for(int i = 0; i < height; i++) blue(pixmap[i], a, width / 4);
            try(DataOutputStream outFile = new DataOutputStream(new FileOutputStream(filename))) {
                outFile.write(header);
                for (int i = 0; i < height; i++) for (int j = 0; j < width; j++) outFile.writeInt(pixmap[i][j]);
            }
            catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static int byteArrayToInt(byte[] array, int index) {
        int value;
        byte[] bufferInt = new byte[0x4];
        System.arraycopy(array, index, bufferInt, 0x0, 0x4);
        ByteBuffer buffer = ByteBuffer.wrap(bufferInt);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        value = buffer.getInt();
        return value;
    }
}

/*class prog {
	public static native int sum(int a, int b);
	static {System.loadLibrary("prog");}
	public static void main(String[] arguments) {
		System.out.println("Sum is " + sum(144, 144));
	}
}*/