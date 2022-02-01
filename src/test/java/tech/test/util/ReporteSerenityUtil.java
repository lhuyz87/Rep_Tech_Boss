package tech.test.util;

import net.thucydides.core.steps.ExecutedStepDescription;
import net.thucydides.core.steps.StepEventBus;
import org.apache.commons.lang3.StringUtils;

public class ReporteSerenityUtil {
	
	public void showStepMessage(String message) {
        // TODO: escape message string for use in HTML
        String escapedMessage = StringUtils.replace(message, " ", "&nbsp;");

        StepEventBus.getEventBus().stepStarted(ExecutedStepDescription.withTitle(escapedMessage));
        StepEventBus.getEventBus().stepFinished();
    }


}
