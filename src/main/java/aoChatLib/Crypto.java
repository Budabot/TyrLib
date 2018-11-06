package aoChatLib;

import java.util.Random;
import java.math.BigInteger;

/*
 * @author tzushia
 * 		taken from: http://aodevs.com/index.php/topic,42.0.html with some modifications 
 */

public class Crypto {
	public static String generateKey(String username, String password, String loginSeed) {
		String loginString = username + "|" + loginSeed + "|" + password;
		String randomPrefix = randomHexString(8);

		//Prime base
		BigInteger dhN = new BigInteger("eca2e8c85d863dcdc26a429a71a9815ad052f6139669dd659f98ae159d313d13c6bf2838e10a69b6478b64a24bd054ba8248e8fa778703b418408249440b2c1edd28853e240d8a7e49540b76d120d3b1ad2878b1b99490eb4a2a5e84caa8a91cecbdb1aa7c816e8be343246f80c637abc653b893fd91686cf8d32d6cfe5f2a6f", 16);
		
		//Funcom public key ( = dhG ^ funcom secret key modulo dhN)
		BigInteger dhY = new BigInteger("9c32cc23d559ca90fc31be72df817d0e124769e809f936bc14360ff4bed758f260a0d596584eacbbc2b88bdd410416163e11dbf62173393fbc0c6fefb2d855f1a03dec8e9f105bbad91b3437d8eb73fe2f44159597aa4053cf788d2f9d7012fb8d7c4ce3876f7d6cd5d0c31754f4cd96166708641958de54a6def5657b9f2e92", 16);
		
		//Generator
		BigInteger dhG = new BigInteger("5", 16);
	    
		//Random 256 bit number	= our secret key
		BigInteger dhx = new BigInteger(randomHexString(32), 16);

		//dhX = dhG^dhx modulo dhN = our public key
		BigInteger dhX = dhG.modPow(dhx, dhN);
	    
		//dhK = dhY^dhx modulo dhN ( dhY^dhx = (dhG^funcom hemlig nyckel)^dhx
		// = the shared secret used to encrypt)
		BigInteger dhK = dhY.modPow(dhx, dhN);
		String dhK_string = dhK.toString(16);
		while (dhK_string.length() < 32)
			dhK_string += "0";
		dhK_string = dhK_string.substring(0, 32);

		//Length of loginInfo as 4 byte int
		int l = loginString.length();
		byte[] b= new byte[] {(byte) (l >> 24), (byte) (l >> 16), (byte) (l >> 8), (byte) l };
		String loginInfoLength = new String(b);	    
	    
		//toBeEncrypted needs to be 8*n long, add padding
		String toBeEncrypted = randomPrefix + loginInfoLength + loginString;
		while ((toBeEncrypted.length() % 8) != 0) {
			toBeEncrypted += " ";
		}
	    
		//Encrypt it with TEA
		String encrypted = encrypt(dhK_string, toBeEncrypted);
		
		return dhX.toString(16) + "-" + encrypted;
	}

	private static String encrypt(String key, String source) {
		//Unpack key and source into little-endian int arrays
		//Iterate through the source array, apply encryption algorithm to each
		//element and convert back to hexstring
		int[] keyValues = hexStringToLittleEndianIntArray(key);
		int[] sourceValues = stringToLittleEndianIntArray(source);
		
		String result = "";
		int[] temp = new int[]{0, 0};
		for(int i = 0; i < sourceValues.length; i += 2) {
			temp[0] = sourceValues[i] ^ temp[0];
			temp[1] = sourceValues[i + 1] ^ temp[1];
			
			//TEA
			int sum = 0;
			int delta = 0x9e3779b9;
			for (int k = 0; k < 32; k++) {
				sum += delta;
				temp[0] += (temp[1] << 4 & 0xfffffff0) + keyValues[0] ^ temp[1] + sum ^ (temp[1] >> 5 & 0x7ffffff) + keyValues[1];
				temp[1] += (temp[0] << 4 & 0xfffffff0) + keyValues[2] ^ temp[0] + sum ^ (temp[0] >> 5 & 0x7ffffff) + keyValues[3];
			}
			result += littleEndianIntToHexString(temp[0]) + littleEndianIntToHexString(temp[1]);
		}
		return result;
	}
	
	private static String littleEndianIntToHexString(int value) {
		String result = "";
		for (int shift = 0; shift < 32; shift += 8) {
			int temp = value >> shift & 255;
			result += temp < 16 ? "0" + Integer.toHexString(temp) : Integer.toHexString(temp);
		}
		return result;
	}
	
	public static String randomHexString(int length) {
		Random rand = new Random(System.currentTimeMillis());
		String randomPrefix = "";
		for(int i = 0; i < length; i += 2) {
			String s = Integer.toHexString(rand.nextInt() & 255);
			randomPrefix = s.length() > 1 ? randomPrefix + s: randomPrefix + "0" + s;
		}
		return randomPrefix;
	}
	
	private static int[] hexStringToLittleEndianIntArray(String str) {
		int[] ints = new int[str.length()/8];
		int pos = 0;
		int shift = 0;
		for (int i = 0; i < str.length(); i += 2) {
			String s = str.substring(i, i + 2);
			ints[pos] |= Integer.parseInt(s, 16) << shift;
			shift += 8;
			if (shift == 32) {
				pos++;
				shift = 0;
			}
		}
		return ints;
	}

	private static int[] stringToLittleEndianIntArray(String str) {
		int[] ints = new int[str.length()/4];
		int pos = 0;
		int shift = 0;
		for (int i = 0; i < str.length(); i++) {
			ints[pos] |= str.charAt(i) << shift;
			shift += 8;
			if (shift == 32) {
				pos++;
				shift = 0;
			}
		}
		return ints;
	}
}