import hrzhao.utils.WeChatHelper;
//from bedroom
public class MainRun {

	public MainRun() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		UserService userService = new UserService();
//		userService.checkUser("admin", "202cb962ac59075b964b07152d234b70");
		System.out.println(WeChatHelper.checkSafe("token", "timestamp", "nonce", "signature"));
	}

}
