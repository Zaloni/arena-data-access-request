# Data Access Request Plugins for Arena
This repository contains data access request related plugin framework and its sample plugin implementations.

From Arena, for any entity, the approval for the access to data can be delegated to an external Approval system such as ServiceNow. 
There is additionally a hook to do tasks post approval - such as fulfillment or running additional work.
Please check the **Arena User Guide on 'Data Access Request'** for more details on how a post approval flow can be triggered from an external system.

This repository contains two projects 'data-access-request-plugin' and 'servicenow-plugin'.

The following steps can be performed to build and integrate a plugin jar with Arena:

1. Use the `DataAccessRequestApprover` interface inside `data-access-request-plugin` for creating data access request implementation for an Approval System.
2. Use the `DataAccessRequestPostApprovalHook` interface inside `data-access-request-plugin` for creating any post data access request implementation.
3. See the `ServiceNowDataAccessApprover` and `ServiceNowPostApprovalHook` inside the sample `servicenow-plugin` project for example implementations that work with ServiceNow.
4. Build the `data-access-request-plugin` first so that the sample `servicenow-plugin` project can find its dependency while mvn build. Need to do the same for any custom implementation. The dependency of `data-access-request-plugin` should be there in any custom project so that the interfaces are resolvable.
5. Finally, build the sample `servicenow-plugin` project or any custom custom implementated project to generate a compatible jar.
6. Ensure that the newly generated .jar file is available in the path `/opt/zdp/zdp-gateway/lib/plugins/data-access-request/`, where zdp-gateway is installed.
7. Integrate any custom approval class and post approval class to Arena by adding them to `/etc/zdp-gateway/zdp-gateway.yml` as shown below for the sample ServiceNow plugin related classes.
```yaml
# Data Access Request related properties
data-access-request:
    approval-impl-class: com.zaloni.bedrock.servicenow.approver.ServiceNowDataAccessApprover
    post-approval-impl-class: com.zaloni.bedrock.servicenow.hook.ServiceNowPostApprovalHook
```
8. Configure any custom properties to be used with the plugin in the `/etc/zdp-gateway/data-access-request/plugin-config.yml`. The sample ServiceNow implementation contains the following properties.
```yaml
# This plugin-config.yml file can have custom properties that will be used by the approval plugin
# that integrates with Arena. Arena will propagate these to the approval plugin implementation.

# Below are the sample properties associated with the sample ServiceNow plugin that comes packaged with Arena.

servicenow:
    username:
    password:     # Wrap with ENC( ) to provide Arena encrypted passwords
    base_url:     # e.g. https://dev12345.service-now.com
    namespace:    # e.g. now
    table_name:   # e.g. x_646741_arena_s_0_approval_details
    fieldsMapping:
        arenaEntities: arena_entity_details
        arenaSdarRequestid: arena_sdar_requestid
        requestedFor: requested_for
        requestedReason: requested_reason
        arenaAdditionalDetails: arena_additional_details # will be sent in json format

```

9. Restart zdp-gateway service.
10. Go to the Arena Web UI and submit a data access request for any entity. 

## License

These files are provided as-is without warranty and are licensed under the Apache License 2.0
