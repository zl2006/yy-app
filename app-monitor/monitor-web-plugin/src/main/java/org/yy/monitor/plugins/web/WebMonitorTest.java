package org.yy.monitor.plugins.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.yy.framework.util.http.WebUtils;
import org.yy.monitor.core.PluginTest;
import org.yy.monitor.core.entity.TestResult;
import org.yy.monitor.plugins.web.data.WebEntity;
import org.yy.monitor.plugins.web.data.WebEntityItem;

@Component("webmonitortest")
public class WebMonitorTest implements PluginTest<WebEntity, WebEntityItem> {

	private static Logger logger = LoggerFactory
			.getLogger(WebMonitorTest.class);

	@Override
	public TestResult test(WebEntity entity, WebEntityItem entityItem) {

		TestResult testResult = new TestResult();

		String url = entity.getUrl() + entityItem.getUrl();
		String params = entityItem.getParams();

		try {
			String result = "";
			String charset = "UTF-8";
			if(StringUtils.isNotEmpty(entityItem.getCharset())){
				charset = entityItem.getCharset();
			}
			if ("GET".equals(entityItem.getMethod())) {
				result = WebUtils.doGet(url, getUrlParams(params),
						entityItem.getCharset());
			} else {
				result = WebUtils.doPost(url, getUrlParams(params),charset, 30000, 30000);
			}
			testResult.setResponse(result);
			testResult.setStatus(judge(result, entityItem.getResp()));
		} catch (IOException e) {
			testResult.setStatus(false);
			testResult.setResponse("" + e);
			logger.error("test error！", e);
		}
		return testResult;
	}

	//处理参数
	protected Map<String, String> getUrlParams(String param) {
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtils.isBlank(param)) {
			return map;
		}
		String[] params = param.split("&");
		for (int i = 0; i < params.length; i++) {
			String[] p = params[i].split("=");
			if (p.length == 2) {
				map.put(p[0], p[1]);
			}
		}
		return map;
	}
	
	//判断结果 
	protected boolean judge(String actualResponse, String expectResponse){
		Pattern p = Pattern.compile(expectResponse);
		Matcher m = p.matcher(actualResponse);
		return actualResponse.contains(expectResponse)  || m.matches();
	}
}
