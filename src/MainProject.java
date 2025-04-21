
public class MainProject {
	public static void main(String[] args) {
		// 주의 : 멘탈이 나갔기에 다른 클래스는 보지 말아주세요.
		ExcelRead excel = new ExcelRead();
		MainFrame frame = new MainFrame(excel.MEMORY);
	}
}
