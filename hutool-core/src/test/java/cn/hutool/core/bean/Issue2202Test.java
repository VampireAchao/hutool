package cn.hutool.core.bean;

import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.text.NamingCase;
import lombok.Data;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Issue2202Test {

	/**
	 * https://github.com/dromara/hutool/issues/2202
	 */
	@Test
	public void mapToBeanWithFieldNameEditorTest(){
		Map<String, String> headerMap = new HashMap<>(5);
		headerMap.put("wechatpay-serial", "serial");
		headerMap.put("wechatpay-nonce", "nonce");
		headerMap.put("wechatpay-timestamp", "timestamp");
		headerMap.put("wechatpay-signature", "signature");
		ResponseSignVerifyParams case1 = BeanUtil.toBean(headerMap, ResponseSignVerifyParams.class,
				CopyOptions.create().setFieldNameEditor(field -> NamingCase.toCamelCase(field, '-')));

		assertEquals("serial", case1.getWechatpaySerial());
		assertEquals("nonce", case1.getWechatpayNonce());
		assertEquals("timestamp", case1.getWechatpayTimestamp());
		assertEquals("signature", case1.getWechatpaySignature());
	}

	@Data
	static class ResponseSignVerifyParams {
		private String wechatpaySerial;
		private String wechatpaySignature;
		private String wechatpayTimestamp;
		private String wechatpayNonce;
		private String body;
	}
}
