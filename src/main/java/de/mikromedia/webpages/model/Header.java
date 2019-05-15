package de.mikromedia.webpages.model;

import de.deepamehta.core.Association;
import de.deepamehta.core.RelatedTopic;
import de.deepamehta.core.Topic;
import de.deepamehta.core.service.CoreService;
import static de.mikromedia.webpages.WebpageService.BUTTON;
import static de.mikromedia.webpages.WebpageService.HEADER_TITLE;
import static de.mikromedia.webpages.WebpageService.DEEPAMEHTA_FILE;
import static de.mikromedia.webpages.WebpageService.FILE_PATH;
import static de.mikromedia.webpages.WebpageService.HEADER;
import static de.mikromedia.webpages.WebpageService.HEADER_CONTENT;
import static de.mikromedia.webpages.WebpageService.ROLE_DEFAULT;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import static de.mikromedia.webpages.WebpageService.BACKGROUND_COLOR;
import static de.mikromedia.webpages.WebpageService.BUTTON_TITLE;
import static de.mikromedia.webpages.WebpageService.DEFAULT_ATTACHMENT;
import static de.mikromedia.webpages.WebpageService.DEFAULT_SIZE;
import static de.mikromedia.webpages.WebpageService.FONT_COLOR;
import static de.mikromedia.webpages.WebpageService.IMAGE_ATTACHMENT_STYLE;
import static de.mikromedia.webpages.WebpageService.IMAGE_LARGE;
import static de.mikromedia.webpages.WebpageService.IMAGE_SIZE_STYLE;
import static de.mikromedia.webpages.WebpageService.IMAGE_SMALL;

public class Header {

    private Topic pageHeader;
    private RelatedTopic imageLarge;
    private RelatedTopic imageSmall;

    private Logger log = Logger.getLogger(getClass().getName());

    public Header(Topic pageHeader) {
        this.pageHeader = pageHeader;
        if (!isHeaderTopic()) {
            throw new IllegalArgumentException("Given topic is not of type Header");
        }
        this.pageHeader.loadChildTopics();
    }

    public Header(long topicId, CoreService dms) {
        this.pageHeader = dms.getTopic(topicId);
        if (!isHeaderTopic()) {
            throw new IllegalArgumentException("Given topic is not of type Header");
        }
        this.pageHeader.loadChildTopics();
    }

    public long getId() {
        return this.pageHeader.getId();
    }

    public Topic getTopic() {
        return this.pageHeader;
    }

    // --- Custom Section Data Accessors
    
    public String getTitle() {
        return this.pageHeader.getChildTopics().getStringOrNull(HEADER_TITLE);
    }

    public String getContent() {
        return this.pageHeader.getChildTopics().getStringOrNull(HEADER_CONTENT);
    }

    public List<Button> getButtons() {
        List<Button> headerButtons = new ArrayList();
        List<RelatedTopic> buttons = this.pageHeader.getChildTopics().getTopicsOrNull(BUTTON);
        if (buttons != null) {
            for (RelatedTopic topic : buttons) {
                Topic buttonTitle = topic.getChildTopics().getTopicOrNull(BUTTON_TITLE);
                if (buttonTitle != null && !buttonTitle.getSimpleValue().toString().isEmpty()) {
                    Button button = new Button(topic);
                    headerButtons.add(button);
                }
            }
        }
        return headerButtons;
    }

    public String getSmallImage() {
        if (this.imageSmall != null)
            return this.imageSmall.getChildTopics().getStringOrNull(FILE_PATH);
        this.imageSmall = this.pageHeader.getRelatedTopic(IMAGE_SMALL, ROLE_DEFAULT,
                ROLE_DEFAULT, DEEPAMEHTA_FILE);
        return (this.imageSmall == null) ? "" : this.imageSmall.getChildTopics().getStringOrNull(FILE_PATH);
    }

    public String getSmallImageSize() {
        String val = null;
        if (imageSmall == null) getSmallImage();
        if (imageSmall != null) {
            Association imageConfig = imageSmall.getRelatingAssociation();
            val = imageConfig.getChildTopics().getStringOrNull(IMAGE_SIZE_STYLE);
        }
        return (val == null) ? DEFAULT_SIZE : val.toLowerCase();
    }

    public String getSmallImageAttachment() {
        String val = null;
        if (imageSmall != null) {
            Association imageConfig = imageSmall.getRelatingAssociation();
            val = imageConfig.getChildTopics().getStringOrNull(IMAGE_ATTACHMENT_STYLE);
        }
        return (val == null) ? DEFAULT_ATTACHMENT : val.toLowerCase();
    }

    public String getLargeImage() {
        if (this.imageLarge != null)
            return this.imageLarge.getChildTopics().getStringOrNull(FILE_PATH);
        this.imageLarge = this.pageHeader.getRelatedTopic(IMAGE_LARGE, ROLE_DEFAULT,
                ROLE_DEFAULT, DEEPAMEHTA_FILE);
        return (this.imageLarge == null) ? "" : this.imageLarge.getChildTopics().getStringOrNull(FILE_PATH);
    }

    public String getLargeImageSize() {
        String val = null;
        if (imageLarge == null) getLargeImage();
        if (imageLarge != null) {
            Association imageConfig = imageLarge.getRelatingAssociation();
            val = imageConfig.getChildTopics().getStringOrNull(IMAGE_SIZE_STYLE);
        }
        return (val == null) ? DEFAULT_SIZE : val.toLowerCase();
    }

    public String getLargeImageAttachment() {
        String val = null;
        if (imageLarge != null) {
            Association imageConfig = imageLarge.getRelatingAssociation();
            val = imageConfig.getChildTopics().getStringOrNull(IMAGE_ATTACHMENT_STYLE);
        }
        return (val == null) ? DEFAULT_ATTACHMENT : val.toLowerCase();
    }

    public String getBackgroundColor() {
        return this.pageHeader.getChildTopics().getStringOrNull(BACKGROUND_COLOR);
    }

    public String getFontColor() {
        return this.pageHeader.getChildTopics().getStringOrNull(FONT_COLOR);
    }

    public JSONObject toJSON() {
        try {
            return new JSONObject()
                .put("title", getTitle())
                .put("buttons", getButtons())
                .put("font_color", getFontColor())
                .put("bg_color", getBackgroundColor());
        } catch (JSONException ex) {
            Logger.getLogger(Webpage.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private boolean isHeaderTopic() {
        if (this.pageHeader == null) return false;
        return (this.pageHeader.getTypeUri().equals(HEADER));
    }
    
}
