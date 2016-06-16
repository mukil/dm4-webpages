package de.mikromedia.webpages.migrations;

import de.deepamehta.accesscontrol.AccessControlPlugin;
import de.deepamehta.accesscontrol.AccessControlService;
import de.deepamehta.core.Topic;
import de.deepamehta.core.Association;
import de.deepamehta.core.TopicType;
import de.deepamehta.core.service.Migration;
import de.deepamehta.core.service.Inject;
import de.deepamehta.workspaces.WorkspacesService;

public class Migration2 extends Migration {

    @Inject WorkspacesService workspacesService;
    @Inject AccessControlService accessControlService;

    @Override
    public void run () {

        // 1) Assign standard site topic and assoc to WS "DeepaMehta", make admin's our standard site public.
        Topic deepaMehtaWorkspace = workspacesService.getWorkspace(WorkspacesService.DEEPAMEHTA_WORKSPACE_URI);
        Topic siteTopic = dm4.getTopicByUri("de.mikromedia.standard_site");
        workspacesService.assignToWorkspace(siteTopic, deepaMehtaWorkspace.getId());

        // 2) Our global/standard/default "website" is the website of user "admin" (we do this now to support multi-sites later)
        Topic adminTopic = accessControlService.getUsernameTopic(AccessControlService.ADMIN_USERNAME);
        Association assoc = dm4.createAssociation(mf.newAssociationModel("dm4.core.association",
                mf.newTopicRoleModel(adminTopic.getId(), "dm4.core.default"),
                mf.newTopicRoleModel(siteTopic.getId(), "dm4.core.default")));
        workspacesService.assignToWorkspace(assoc, deepaMehtaWorkspace.getId());

        // 3) Type workspace assignments to new "Administration" workspace
        Topic administrationWorkspace = dm4.getAccessControl()
            .getWorkspace(AccessControlPlugin.ADMINISTRATION_WORKSPACE_URI);
        // Note: Child topic types are currently not assigned to any workspace
        TopicType siteType = dm4.getTopicType("de.mikromedia.site");
        TopicType menuItemType = dm4.getTopicType("de.mikromedia.menu.item");
        TopicType redirectType = dm4.getTopicType("de.mikromedia.redirect");
        TopicType pageType = dm4.getTopicType("de.mikromedia.page");
        workspacesService.assignTypeToWorkspace(siteType, administrationWorkspace.getId());
        workspacesService.assignTypeToWorkspace(menuItemType, administrationWorkspace.getId());
        workspacesService.assignTypeToWorkspace(redirectType, administrationWorkspace.getId());
        workspacesService.assignTypeToWorkspace(pageType, administrationWorkspace.getId());

    }

}
