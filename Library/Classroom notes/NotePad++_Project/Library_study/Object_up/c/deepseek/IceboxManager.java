public class IceboxManager{
	public static void main(String[] args){
		IceboxManager IceboxManager = new IceboxManager();
		Manager David = IceboxManager.new Manager();
		David.Take(10);
		
	}
	
	class Manager {
		private int iceCream_Count = 30;
		public int Take(int num){
			if(iceCream_Count >= num){
				iceCream_Count -= num;
				System.out.println("ȡ��" + num + "�������");
				return num;
			}else{
				System.out.println("���������������!");
				return 0;
			}
		}
	}
}