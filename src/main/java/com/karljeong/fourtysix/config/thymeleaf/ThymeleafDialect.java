package com.karljeong.fourtysix.config.thymeleaf;

import java.util.Collections;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

import com.karljeong.fourtysix.utils.ThymeleafDialectUtil;

@Component
public class ThymeleafDialect extends AbstractDialect implements IExpressionObjectDialect {

	protected ThymeleafDialect() {
		super("fourtysixFormat");
	}

	@Override
	public IExpressionObjectFactory getExpressionObjectFactory() {
		return new IExpressionObjectFactory() {

			@Override
			public boolean isCacheable(String expressionObjectName) {
				return true;
			}

			@Override
			public Set<String> getAllExpressionObjectNames() {
				return Collections.singleton("fourtysixFormat");
			}

			@Override
			public Object buildObject(IExpressionContext context, String expressionObjectName) {
				return new ThymeleafDialectUtil();
			}
		};
	}

}
