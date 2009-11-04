package play.data.validation;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;

public class MatchCheck extends AbstractAnnotationCheck<Match> {

	private static final long serialVersionUID = 3582799870785146808L;
	final static String mes = "validation.match";
    Pattern pattern = null;

    @Override
    public void configure(Match match) {
        setMessage(match.message());
        pattern = Pattern.compile(match.value());
    }

    public boolean isSatisfied(Object validatedObject, Object value, OValContext context, Validator validator) {
        if (value == null || value.toString().length() == 0) {
            return true;
        }
        return pattern.matcher(value.toString()).matches();
    }

    @Override
    public Map<String, String> createMessageVariables() {
        Map<String, String> messageVariables = new HashMap<String, String>();
        messageVariables.put("pattern", pattern.toString());
        return messageVariables;
    }

}
