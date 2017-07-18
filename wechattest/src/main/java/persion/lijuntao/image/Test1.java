package persion.lijuntao.image;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.binary.Base64;


public class Test1 {
	public static void main(String[] args) {
//		int[][] iss = new int[][]{{1,2,3}, {4,5,6}, {7,8,9}};
//		Integer[][][] iss = new Integer[][][]{{ {1,5,78},{2,34,76},{3,56}}, {{4},{5},{6}}, {{7},{8},{9}}};
//		Integer[] iss = new Integer[]{};
//		Integer[] iss = null;
//		int[] iss = new int[]{1,2,3};
//		int[] iss = new int[]{};
		int[] iss = null;
//		String[] iss = new String[]{"11","21","31"};
//		String[][] iss = new String[][]{{"1","2","3"},{"11","21","31"}};
//		Test1[][] iss = new Test1[][]{{new Test1(), new Test1()}, {new Test1(), new Test1()}};
		print(new BigDecimal("02.2"));
		print(new BigDecimal(12.2222342));
		print(new BigDecimal(12.233345));
		print(new BigDecimal(12.224345));
		print(new BigDecimal(12.2252345));
		print(new BigDecimal(12.2262345));
		print(new BigDecimal(12.2272345));
		print(new BigDecimal(12.2282345));
		print(new BigDecimal(12.2292345));
		print(new BigDecimal(12.2202345));
		print(new BigDecimal(-12.211124));
		print(new BigDecimal(-12.2222342));
		print(new BigDecimal(-12.233345));
		print(new BigDecimal(-12.224345));
		print(new BigDecimal(-12.2252345));
		print(new BigDecimal(-12.2262345));
		print(new BigDecimal(-12.2272345));
		print(new BigDecimal(-12.2282345));
		print(new BigDecimal(-12.2292345).abs());
		print(new BigDecimal(-12.2202345));
		print(new BigDecimal(-12.2202345).abs());
		print(new BigDecimal(1223.2202345).abs());
		List<Object> list = getListByNotBaseTypeArratObject(new ArrayList(), iss);
		
		
		
		
		System.out.println(Arrays.toString(list.toArray()));
		
		byte[] encodeBase64 = Base64.encodeBase64(null);
		System.out.println(String.format("jkasdasdl; asdal;d asd;as%s,%s,dl as ;d asf", "asdfasdf", "asdg"));
	}
	
	/**
	 * @description 把非基础类型的数组转为集合
	 * 如果为基础类型，放回空集合
	 * 如果类型不为数组，返回该类型集合
	 * obj如果为空，返回空聚合
	 * list可为空
	 * @author 李俊涛
	 * @date 2017年6月27日
	 * @param list 
	 * @param obj
	 * @return
	 */
	public static <T> List<Object> getListByNotBaseTypeArratObject(List<Object> list, Object obj){
		try{
			if(list == null){
				list = new ArrayList<Object>();
			}
			if(obj == null){
				return list;
			}
			
			if(isArray(obj)){
				if( !isBaseTypeArray(obj)){
					//基础类型不能强转
					Object[] objs = (Object[])obj;
					for(Object obj2 :objs){
						getListByNotBaseTypeArratObject(list, obj2);
					}
				}	
			}else{
				list.add(obj);
			}
		}catch(Exception e){
			
		}
		return list;
	}
	
	public static boolean isBaseType(Object obj){
		if(obj == null){
			return false;
		}
		return obj.getClass().isPrimitive();
	}
	
	public static boolean isBaseTypeArray(Object obj){
		if(isArray(obj)){
			Class<?> type = obj.getClass().getComponentType();
			if(type!=null && type.isPrimitive()){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isArray(Object obj){
		if(obj == null){
			return false;
		}
		return obj.getClass().isArray();
	}
	
	public static void print(BigDecimal amount){
		if(amount != null){
			//数字格式化
			DecimalFormat format = new DecimalFormat();
			//format.setMaximumFractionDigits(2);
			//format.setGroupingSize(0);
			format.setRoundingMode(RoundingMode.FLOOR);
			
			format = new DecimalFormat("###0.00");
			System.out.println(amount.toString() + "=" + format.format(amount));
		}
	}
}
