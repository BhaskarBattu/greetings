package com.apptmyz.fpgreetings.services;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.security.Key;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.apptmyz.fpgreetings.response.GeneralResponse;
import com.apptmyz.fpgreetings.utils.FilesUtil;
import com.apptmyz.fpgreetings.utils.JwtUtil;

@Service
public class BaseService
{
	public static final Logger logger = Logger.getLogger(BaseService.class);

	private static final String ALGO = "AES"; // Default uses ECB PKCS5Padding

	@Autowired
	RestTemplate resttemplate;

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	private FilesUtil filesUtil;


	public String getCellValueAccordingToCellType(Cell cell) {
		String cellValue = "";
		String value = "";
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				value = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (!HSSFDateUtil.isCellDateFormatted(cell)) {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					value = cell.getStringCellValue();
				} else {
					value = null;
				}
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				value = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_BLANK:
				value = null;
				break;
			default:
				if (cell.getColumnIndex() == 1 && cell.getCellType() == 2)
					value = cell.toString();
				else
					value = null;
				break;
			}
		} else {
			value = null;
		}
		cellValue = value;

		return cellValue;
	}

	public String getCsvFromList(List<Integer> list) {
		String csv = "[";
		try {
			for (Integer integer : list) {
				csv = csv + integer + ", ";
			}
			csv = csv.substring(0, csv.length() - 2) + "]";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return csv;
	}


	public Date ChangeDateFormatSpecific(Cell cbDateCellValue)
	{
		Date date = null;
		String cbDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		try 
		{   
			//			cbDate = getCellValueAccordingToCellType(cbDateCellValue);
			cbDate = String.valueOf(cbDateCellValue);
			date = sdf.parse(cbDate+" 00:00:00");
		} 
		catch (ParseException e)
		{
			try
			{
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
				SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy");
				SimpleDateFormat dateFormatter=new SimpleDateFormat("dd-MM-yyyy");
				if(String.valueOf(cbDate).length()==9)
				{
					Date ex = dateFormat.parse(String.valueOf(cbDate));
					String s=dateFormatter.format(ex);
					date = dateFormatter.parse(s);
				}
				if(String.valueOf(cbDate).length() == 11)
				{
					Date ex=dateFormat1.parse(String.valueOf(cbDate));
					String s = dateFormatter.format(ex);
					date = dateFormatter.parse(s);
				}
			}
			catch(ParseException e1)
			{
				logger.error("Parse exception internal", e1);
				date = null;
			}
		}
		catch(Exception e2)
		{
			logger.error("Parse exception internal", e2);
			date = null;
		}
		return date;
	}

	public Date ChangeDateFormatSpecific1(String cbDateCellValue)
	{
		Date date = null;
		String cbDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
		try 
		{   
			cbDate = String.valueOf(cbDateCellValue);
			date = sdf.parse(cbDate);
		} 
		catch (ParseException e)
		{
			try
			{
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				SimpleDateFormat dateFormatter=new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
				SimpleDateFormat dateFormatter1=new SimpleDateFormat("dd-MM-yyyy");
				if(String.valueOf(cbDate).length()==19)
				{
					date = dateFormat.parse(String.valueOf(cbDate));
				}
				if(String.valueOf(cbDate).length()==18)
				{
					date = dateFormatter.parse(String.valueOf(cbDate));
				}
				if(String.valueOf(cbDate).length()==10)
				{
					date = dateFormatter1.parse(String.valueOf(cbDate));
				}
			}
			catch(ParseException e1)
			{
				logger.error("Parse exception internal", e1);
				date = null;
			}
		}
		catch(Exception e2)
		{
			logger.error("Parse exception internal", e2);
			date = null;
		}
		return date;
	}

	public String changeTimeFormatSpecific(Cell txnTimeCell)
	{
		String timeStamp = null;
		try
		{
			SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
			if(txnTimeCell != null && txnTimeCell.getDateCellValue()!=null){
				timeStamp =formatTime.format(txnTimeCell.getDateCellValue());
			}
		}
		catch (Exception e) {
			logger.error("Parse exception internal", e);
		}
		return timeStamp;
	}

	public ResponseEntity<GeneralResponse> errorResponse(boolean status,String errorMsg,long statusCode, Object data)
	{
		logger.info("Error Response Occured: status:"+ status+",errorMeg:"+errorMsg+",Error status code:"+statusCode);		
		GeneralResponse errorResponse = new GeneralResponse(status,errorMsg,statusCode,data);
		logger.info("Erros hadled Successfully");
		return new ResponseEntity<GeneralResponse>(errorResponse,HttpStatus.OK);
	}

	public ResponseEntity<GeneralResponse> successResponse(boolean status,String msg,long statusCode, Object data)
	{
		logger.info("Response Occured: status:"+ status+",Msg:"+msg+",status code:"+statusCode);
		GeneralResponse response = new GeneralResponse(status,msg,statusCode,data);
		logger.info(" Successfully");
		return new ResponseEntity<GeneralResponse>(response,HttpStatus.OK);
	}

	public void errorResponse(boolean status,String errorMsg, Object data)
	{
		logger.error("Error Response Occured: status:"+ status+",errorMeg:"+errorMsg+",data:"+data);

	}


	public boolean isEmpty(String str)
	{
		if(str != null)
		{
			if(str.length() == 0)
				return true;
			else if(str.trim().length() == 0)
				return true;
			else 
				return false;
		}
		else
			return true;
	}


	private void convertListToSettlementTypeParentSuperMercantList(Map<String,String> map, List<Object[]> list, String settleto)
	{
		for(int i=list.size() - 1 ; i >= 0; i--)
		{
			String key = settleto +"-"+ ((Integer) list.get(i)[0]) ;
			if(!map.containsKey(key))
			{
				map.put(key, (String) list.get(i)[1]);
			}				
		}
	}


	public File[] getAllFilesInDictionary(final String startwith)  throws Exception
	{
		File[] files = null;

		try{

			File directory = new File(filesUtil.getProperty("snorkelFilesPath"));

			logger.info("directory exits or not: "+ directory.getPath());
			if (!(directory.exists() && directory.isDirectory()))
			{
				logger.error(String.format("Directory %s does not exist", directory));
				return null;
			}

			FileFilter logFilefilter = new FileFilter() {
				public boolean accept(File file) {
					if (file.getName().startsWith(startwith)) {
						return true;
					}
					return false;
				}
			};

			files = directory.listFiles(logFilefilter);
			logger.info("directory exits files: "+ files.length);
			return files;

		}catch (Exception e) {

			logger.info("Print stack trace", e);

			errorResponse(false,"Error Occured Reading all files from dictionary",null);

			throw e;
		}
	}


	public void closeBufferedReader(BufferedReader br)
	{
		try {
			if (br != null)
				br.close();
		}
		catch (IOException ex) {
			logger.error("Exception occured at Buffered Reader line");
			ex.printStackTrace();
		}
	}

	public void closeFileReader(FileReader fr)
	{
		try {
			if (fr != null)
				fr.close();
		}
		catch (IOException ex) {
			logger.error("Exception occured at File Reader line");
			ex.printStackTrace();
		}
	}

	public Date setformatDate(String format,String date) throws Exception
	{
		SimpleDateFormat dateFormat = null;

		Date fromatedDate = null;

		try {

			dateFormat = new SimpleDateFormat(format);

			fromatedDate = dateFormat.parse(date);

		}
		catch (Exception e) {
			errorResponse(false,"Error Occured checkDateIsNullorNot ",null);			
			throw e;
		}

		return fromatedDate;
	}
	public String encrypt(String Data, String secret) throws Exception {
		Key key = generateKey(secret);
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(Data.getBytes());
		String encryptedValue = Base64.getEncoder().encodeToString(encVal);
		return encryptedValue;
	}

	public String decrypt(String strToDecrypt, String secret) {
		try {			
			Key key = generateKey(secret);
			Cipher cipher = Cipher.getInstance(ALGO);
			cipher.init(Cipher.DECRYPT_MODE, key);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}

	private Key generateKey(String secret) throws Exception {
		byte[] decoded = Base64.getDecoder().decode(secret.getBytes());
		Key key = new SecretKeySpec(decoded, ALGO);
		return key; 
	}

	public String decodeKey(String str) {
		byte[] decoded = Base64.getDecoder().decode(str.getBytes());
		return new String(decoded);
	}

	public String encodeKey(String str) {
		byte[] encoded = Base64.getEncoder().encode(str.getBytes());
		return new String(encoded);
	}

	public String createImageFiles(String dob, String encodedImage)
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssss");
		Date date = new Date();
		String afileName = null;
		if(encodedImage != null){
			try {
				byte[] decodedString = Base64.getDecoder().decode(encodedImage);
				ByteArrayInputStream bis = new ByteArrayInputStream(decodedString);
				BufferedImage bImage2 = ImageIO.read(bis);

				int width = bImage2.getWidth();
				int height = bImage2.getHeight();
				BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
				int px[] = new int[width * height];
				bImage2.getRGB(0, 0, width, height, px, 0, width);
				output.setRGB(0, 0, width, height, px, 0, width);

				afileName = dob+"_"+format.format(date)+".jpeg";
				String afilePath = filesUtil.getProperty("images.upload.path");
				File outputfile = new File(afilePath,afileName);
				ImageIO.write(output, "jpeg", outputfile);

			} catch (IOException e) {
				logger.info("IO Exception occured saveing image info", e);
				afileName = null;

			}catch (Exception e) {
				logger.info("Exception occured saveing image info", e);
				afileName = null;

			}
		}

		return afileName;
	}
}
