package junit.test;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.mapper.AttrMapper;
import com.atguigu.mapper.SearchMapper;
import com.atguigu.util.mybatis.MyBatisUtils;
import com.atguigu.util.mybatis.RunTest;

public class TestMapper  {
	@Test
	public void test3(){
		new MyBatisUtils(new RunTest<SearchMapper>(){
			@Override
			public void test(SearchMapper t) {
				
				 List<T_MALL_SKU> skus = t.select_sku_by_spu(78);
				for (T_MALL_SKU sku : skus) {
					System.out.println(sku);
				}
			}
		});
	}
	@Test
	public void test2(){
		new MyBatisUtils(new RunTest<SearchMapper>(){
			@Override
			public void test(SearchMapper t) {
				HashMap<String, Object> map = new HashMap<String,Object>();
				map.put("class_2_id", 11);
				List<OBJECT_T_MALL_SKU> sku = t.select_sku_by_class_2(16);
				for (OBJECT_T_MALL_SKU object_T_MALL_SKU : sku) {
					System.out.println(object_T_MALL_SKU);
				}
			}
		});
	}
	
	@Test
	public void test1(){
		new MyBatisUtils(new RunTest<SearchMapper>(){
			@Override
			public void test(SearchMapper t) {
				HashMap<String, Object> map = new HashMap<String,Object>();
				map.put("class_2_id", 11);
				List<OBJECT_T_MALL_SKU> sku = t.select_sku_by_attr(map);
				for (OBJECT_T_MALL_SKU object_T_MALL_SKU : sku) {
					System.out.println(object_T_MALL_SKU);
				}
			}
		});
	}
	
	
	@Test
	public void test() {

		new MyBatisUtils(new RunTest<SearchMapper>(){
			@Override
			public void test(SearchMapper t) {
				List<OBJECT_T_MALL_SKU> select_sku_by_class_2 = t.select_sku_by_class_2(11);
				System.out.println(select_sku_by_class_2);
				//System.out.println("test...");
			}
		});
		
		new MyBatisUtils(new RunTest<AttrMapper>(){
			@Override
			public void test(AttrMapper t) {
				List<OBJECT_T_MALL_ATTR> list = t.select_attr_by_class_2_id("11");
				for (OBJECT_T_MALL_ATTR obj : list) {
					System.out.println(obj);
				}
			}
			
		});
	}
}
