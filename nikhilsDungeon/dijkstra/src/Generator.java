import java.util.Random;


public class Generator {
	public static void main(String[] arg){
		Random generator = new Random();

		int M = 1;
		int N = 70;
		int INFINITY = Integer.MAX_VALUE/2;
		
		System.out.printf("%d %d\n", M, N);
		
		for(int i = 0; i<M; i++) {
			for(int j = 0; j<N;j++) {
				System.out.printf("%d ",generator.nextInt(INFINITY));
			}
			System.out.println();
		}
		System.out.printf("%d %d %d\n", generator.nextInt(M) + 1, generator.nextInt(N) + 1, generator.nextInt(INFINITY));
	}	

}
