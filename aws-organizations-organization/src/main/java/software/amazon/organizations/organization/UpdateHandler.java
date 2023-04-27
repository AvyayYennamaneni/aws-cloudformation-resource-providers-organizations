package software.amazon.organizations.organization;

import software.amazon.awssdk.services.organizations.OrganizationsClient;
import software.amazon.cloudformation.proxy.AmazonWebServicesClientProxy;
import software.amazon.cloudformation.proxy.ProxyClient;
import software.amazon.cloudformation.proxy.ResourceHandlerRequest;
import software.amazon.cloudformation.proxy.ProgressEvent;
import software.amazon.cloudformation.proxy.HandlerErrorCode;
import software.amazon.organizations.utils.OrgsLoggerWrapper;


public class UpdateHandler extends BaseHandlerStd {
    private OrgsLoggerWrapper log;

    protected ProgressEvent<ResourceModel, CallbackContext> handleRequest(
            final AmazonWebServicesClientProxy awsClientProxy,
            final ResourceHandlerRequest<ResourceModel> request,
            final CallbackContext callbackContext,
            final ProxyClient<OrganizationsClient> orgsClient,
            final OrgsLoggerWrapper logger) {

        this.log = logger;
        logger.log(String.format("Entered %s update handler for Organization resource type with account Id [%s].", ResourceModel.TYPE_NAME, request.getAwsAccountId()));

        final ResourceModel previousModel = request.getPreviousResourceState();
        final ResourceModel model = request.getDesiredResourceState();

        if (previousModel == null) {
            logger.log("Previous Organization information is empty, cannot update");
            return ProgressEvent.failed(model, callbackContext, HandlerErrorCode.NotFound,
                    "Organization cannot be updated as no previous Organization exists");
        } else if (model == null) {
            logger.log("Organization cannot be updated as organization is empty");
            return ProgressEvent.failed(model, callbackContext, HandlerErrorCode.InvalidRequest,
                    "Organization cannot be updated");
        } else {
            logger.log("Update operation is not supported");
            return ProgressEvent.failed(ResourceModel.builder().build(), callbackContext, HandlerErrorCode.InvalidRequest,
                    "Update not supported!");
        }
    }
}
