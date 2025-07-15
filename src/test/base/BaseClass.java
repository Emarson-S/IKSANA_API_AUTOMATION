import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.options.RequestOptions;

public class BaseClass {

    public static String origin="http://135.181.193.65:8080/";



    // Playwright instance 

    public static APIRequestContext request(){
        Playwright playwright =Playwright.create();
        APIRequest  apiRequest=playwright.request();
        APIRequestContext context=apiRequest.newContext();
        return context;
    }

    // Get API request without authendication

    public static APIResponse getRequestNoAuth(String URL){
        APIRequestContext context=request();
        APIResponse response=context.get(origin+URL);
       return response;
    }

    // Get API request with authendication

    public static APIResponse getRequestWithAuth(String URL, String authendication, String Type){
        APIRequestContext context=request();
        APIResponse response=context.get(origin+URL, RequestOptions.create().setHeader("Authorization",authendication).setHeader("Application", "Iksana-Base").setHeader("Channel",Type));
       return response;
    }

    
	// Post request with Authendication

	public static APIResponse postRequestWithToken(String URL, String authendication, Object data) {
		APIRequestContext context=request();
		APIResponse response = content.post(origin+ URL,
				RequestOptions.create().setHeader("Authorization", authendication)
						.setHeader("Content-Type", "application/json").setHeader("Application", "Iksana-Base")
						.setData(data));
		return response;
	}

	// Post request without Authendication

	public static APIResponse postRequestWithoutToken(String URL, String Type, Object data) {
		APIRequestContext context=request();
		APIResponse response = content.post(origin + URL,
				RequestOptions.create().setHeader("Content-Type", "application/json").setHeader("Channel", Type)
						.setHeader("Application", "Iksana-Base").setData(data));
		return response;
	}

	// Put request with Authendication

	public static APIResponse putRequestWithToken(String URL, String authendication, Object data) {
		APIRequestContext context=request();
		APIResponse response = content.put(origin + URL,
				RequestOptions.create().setHeader("Authorization", authendication)
						.setHeader("Content-Type", "application/json").setData(data));
		return response;
	}

	// get response body data from API

	public static JsonNode getBodyData(APIResponse response) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode results = objectMapper.readTree(response.body());
		return results;
	}

	// create new Excel Sheet
	public static void tocreateExcelSheet(String fileName, String sheetName, int rowNo, int cellNo, String value)
			throws IOException {
		String fileLocation=folderLocation();
		File f = new File(fileLocation+"\\Iksana_Inputs\\" + fileName + ".xlsx");
		FileInputStream fil = new FileInputStream(f);
		Workbook b = new XSSFWorkbook(fil);
		Sheet sh = b.createSheet(sheetName);
		Row r = sh.createRow(rowNo);
		Cell c = r.createCell(cellNo);
		c.setCellValue(value);
		FileOutputStream fo = new FileOutputStream(f);
		b.write(fo);

	}

	// create new row in old Sheet
	public static void toCreateNewRow(String sheetName, int rowNo, int cellNo, String value)
			throws IOException {
		String fileLocation=folderLocation();		
		File f = new File(fileLocation+"\\Iksana_Inputs.xlsx");
		FileInputStream fil = new FileInputStream(f);
		Workbook b = new XSSFWorkbook(fil);
		Sheet sh = b.getSheet(sheetName);
		Row r = sh.createRow(rowNo);
		Cell c = r.createCell(cellNo);
		c.setCellValue(value);
		FileOutputStream fo = new FileOutputStream(f);
		b.write(fo);

	}

	// create new cell in old row

	public static void toCreateNewCell(String sheetName, int rowNo, int cellNo, String value)
			throws IOException {
		String fileLocation=folderLocation();
		File f = new File(fileLocation+"\\Iksana_Inputs.xlsx");
		FileInputStream fil = new FileInputStream(f);
		Workbook b = new XSSFWorkbook(fil);
		Sheet sh = b.getSheet(sheetName);
		Row r = sh.getRow(rowNo);
		Cell c = r.createCell(cellNo);
		c.setCellValue(value);
		FileOutputStream fo = new FileOutputStream(f);
		b.write(fo);
	}

	public static void resultsCreateNewCell(String sheetName, int rowNo, int cellNo, String value)
			throws IOException {
		String fileLocation=folderLocation();
		File f = new File(fileLocation+"\\Iksana_API_Testing_Testcases_v0.1.xlsx");
		FileInputStream fil = new FileInputStream(f);
		Workbook b = new XSSFWorkbook(fil);
		Sheet sh = b.getSheet(sheetName);
		Row r = sh.getRow(rowNo);
		Cell c = r.createCell(cellNo);
		c.setCellValue(value);
		FileOutputStream fo = new FileOutputStream(f);
		b.write(fo);
	}

	public static String toReadDataFromExcel(String sheetName, int rowNo, int cellNo)
			throws IOException {

		String fileLocation=folderLocation();		
		File f = new File(fileLocation+"\\Iksana_Inputs.xlsx");
		FileInputStream fin = new FileInputStream(f);
		Workbook b = new XSSFWorkbook(fin);
		Sheet sh = b.getSheet(sheetName);
		Cell c = sh.getRow(rowNo).getCell(cellNo);
		int type = c.getCellType();
		String res;
		if (type == 1) {
			res = c.getStringCellValue();
		} else if (DateUtil.isCellDateFormatted(c)) {
			Date da = c.getDateCellValue();
			SimpleDateFormat sim = new SimpleDateFormat("dd/MM/yyyy");
			res = sim.format(da);
		} else {
			double d = c.getNumericCellValue();
			long l = (long) d;
			res = String.valueOf(l);
		}
		return res;

	}

	public static String resultsReadDataFromExcel(String sheetName, int rowNo, int cellNo)
			throws IOException {


        String fileLocation=folderLocation();
		File f = new File(fileLocation+"\\Iksana_API_Testing_Testcases_v0.1.xlsx");
		FileInputStream fin = new FileInputStream(f);
		Workbook b = new XSSFWorkbook(fin);
		Sheet sh = b.getSheet(sheetName);
		Cell c = sh.getRow(rowNo).getCell(cellNo);
		int type = c.getCellType();
		String res;
		if (type == 1) {
			res = c.getStringCellValue();
		} else if (DateUtil.isCellDateFormatted(c)) {
			Date da = c.getDateCellValue();
			SimpleDateFormat sim = new SimpleDateFormat("dd/MM/yyyy");
			res = sim.format(da);
		} else {
			double d = c.getNumericCellValue();
			long l = (long) d;
			res = String.valueOf(l);
		}
		return res;

	}

	// Base64 encryption

	public static String encryption(String data) {
		String code = Base64.getEncoder().encodeToString(data.getBytes());
		return code;
	}

	// Json verification

	public boolean isJSONValid(String test) {

		if (test == null) {
			return false;
		}
		try {
			new JSONObject(test);
		} catch (JSONException ex) {
			try {
				new JSONArray(test);
			} catch (JSONException ex1) {
				return false;
			}
		}
		return true;
	}


	// Find Folder Locations

	public static String folderLocation() {
        File folder = new File("Files");

        
        if (folder.exists() && folder.isDirectory()) {
            String folderLocation = folder.getAbsolutePath();

            // File[] files = folder.listFiles();
            // if (files != null) {
            //     System.out.println("Files in the folder:");
            //     for (File file : files) {
            //         System.out.println(file.getName());
            //     }
            // } else {
            //     System.out.println("The folder is empty or an I/O error occurred.");
            // }

			return folderLocation;

        } else {
            System.out.println("The folder does not exist or is not a directory.");
        }
		return null;
    }



}
