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
				System.out.println("取出" + num + "个冰淇淋");
				return num;
			}else{
				System.out.println("冰淇淋数量不够啦!");
				return 0;
			}
		}
	}
}