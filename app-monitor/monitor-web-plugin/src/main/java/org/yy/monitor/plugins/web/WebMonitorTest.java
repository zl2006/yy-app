package org.yy.monitor.plugins.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.yy.framework.util.net.WebUtils;
import org.yy.monitor.core.MonitorTest;
import org.yy.monitor.core.entity.Entity;
import org.yy.monitor.core.entity.EntityItem;
import org.yy.monitor.core.entity.EntityItemTest;
import org.yy.monitor.core.entity.TestResult;
import org.yy.monitor.core.persistence.EntityItemService;
import org.yy.monitor.core.persistence.EntityItemTestService;
import org.yy.monitor.core.util.EntityUtil;
import org.yy.monitor.plugins.web.data.WebEntity;
import org.yy.monitor.plugins.web.data.WebEntityItem;

@Component("webmonitortest")
public class WebMonitorTest implements MonitorTest {

	@Resource(name = "entityItemService")
	private EntityItemService entityItemService;

	@Resource(name = "entityItemTestService")
	private EntityItemTestService entityItemTestService;

	private static Logger logger = LoggerFactory
			.getLogger(WebMonitorTest.class);

	@Override
	public TestResult test(Entity entity, EntityItem entityItem) {

		WebEntity webEntity = EntityUtil.toPlugEntity(entity, WebEntity.class);
		WebEntityItem webEntityItem = EntityUtil.toPlugEntityItem(entityItem,
				WebEntityItem.class);

		TestResult testResult = new TestResult();

		String url = webEntity.getUrl() + webEntityItem.getUrl();
		String params = webEntityItem.getParams();

		try {
			// 测试
			String result = "";
			String charset = "UTF-8";
			if (StringUtils.isNotEmpty(webEntityItem.getCharset())) {
				charset = webEntityItem.getCharset();
			}
			if ("GET".equals(webEntityItem.getMethod())) {
				result = WebUtils.doGet(url, getUrlParams(params),
						webEntityItem.getCharset());
			} else {
				result = WebUtils.doPost(url, getUrlParams(params), charset,
						30000, 30000);
			}
			testResult.setResponse(result);
			testResult.setStatus(judge(result, webEntityItem.getResp()));

		} catch (IOException e) {
			testResult.setStatus(false);
			testResult.setResponse("" + e);
			logger.error("test error！", e);
		}

		// 记录结果
		if (testResult.isStatus()) {
			webEntityItem.setSuccessTimes(webEntityItem.getSuccessTimes() + 1);
		} else {
			webEntityItem.setFailureTimes(webEntityItem.getFailureTimes() + 1);
		}
		entityItemService.updateEntityItemByTest(webEntityItem);

		EntityItemTest test = new EntityItemTest();
		test.setActulResponse(testResult.getResponse());
		test.setExpectResponse(webEntityItem.getResp());
		test.setItemID(webEntityItem.getItemID());
		test.setParams(webEntityItem.getParams());
		test.setResult(testResult.isStatus());
		test.setTestTime(new Date());
		test.setUrl(url);
		entityItemTestService.insertEntityItemTest(test);

		return testResult;
	}

	// 处理参数
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

	// 判断结果
	protected boolean judge(String actualResponse, String expectResponse) {
		Pattern p = Pattern.compile(expectResponse);
		Matcher m = p.matcher(actualResponse);
		return actualResponse.contains(expectResponse) || m.matches();
	}
}
