package ca.dal.comparify.item.model;

import ca.dal.comparify.model.AuditModel;
import ca.dal.comparify.utils.UUIDUtils;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

/**
 * @author Harsh Shah
 */
public class ItemModel {

    @BsonId
    private String id;

    private String name;

    private String description;

    @BsonProperty("default_image")
    private String defaultImage;

    private AuditModel audit;

    public ItemModel(@BsonId String id,
                     @BsonProperty("name") String name,
                     @BsonProperty("description") String description,
                     @BsonProperty("default_image") String defaultImage,
                     @BsonProperty("audit") AuditModel audit) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.defaultImage = defaultImage;
        this.audit = audit;
    }

    public ItemModel(String name, String description, String defaultImage, AuditModel audit) {
        this.id = UUIDUtils.generate();
        this.name = name;
        this.description = description;
        this.defaultImage = defaultImage;
        this.audit = audit;
    }

    public static ItemModel create(ItemRequestModel model, String createdBy){
        return new ItemModel(model.getName(), model.getDescription(), model.getDefaultImage(), AuditModel.create(createdBy));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(String defaultImage) {
        this.defaultImage = defaultImage;
    }

    public AuditModel getAudit() {
        return audit;
    }

    public void setAudit(AuditModel audit) {
        this.audit = audit;
    }
}
