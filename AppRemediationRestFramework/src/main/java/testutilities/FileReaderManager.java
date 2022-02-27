package testutilities;

public class FileReaderManager {

	private static FileReaderuserdef fileReaderuserdef;
	
	public static FileReaderManager getInstance(){
		return new FileReaderManager();
		}

	public FileReaderuserdef getFileReader() {
		return (fileReaderuserdef == null)  ? new FileReaderuserdef() : fileReaderuserdef;
		}
	}
