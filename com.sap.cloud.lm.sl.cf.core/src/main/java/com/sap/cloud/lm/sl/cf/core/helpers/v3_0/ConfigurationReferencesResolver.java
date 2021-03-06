package com.sap.cloud.lm.sl.cf.core.helpers.v3_0;

import java.util.function.BiFunction;

import com.sap.cloud.lm.sl.cf.core.dao.ConfigurationEntryDao;
import com.sap.cloud.lm.sl.cf.core.helpers.v2_0.ConfigurationFilterParser;
import com.sap.cloud.lm.sl.cf.core.model.CloudTarget;
import com.sap.cloud.lm.sl.cf.core.util.ApplicationConfiguration;
import com.sap.cloud.lm.sl.mta.model.v3_0.RequiredDependency;

public class ConfigurationReferencesResolver extends com.sap.cloud.lm.sl.cf.core.helpers.v2_0.ConfigurationReferencesResolver {

    public ConfigurationReferencesResolver(ConfigurationEntryDao dao, ConfigurationFilterParser filterParser,
        BiFunction<String, String, String> spaceIdSupplier, CloudTarget cloudTarget, ApplicationConfiguration configuration) {
        super(dao, filterParser, spaceIdSupplier, cloudTarget, configuration);
    }

    @Override
    protected ConfigurationReferenceResolver createReferenceResolver(ConfigurationEntryDao dao) {
        return new ConfigurationReferenceResolver(dao, configuration);
    }

    @Override
    protected RequiredDependency createRequiredDependency(com.sap.cloud.lm.sl.mta.model.v1_0.Resource resource,
        com.sap.cloud.lm.sl.mta.model.v2_0.RequiredDependency dependency) {
        RequiredDependency.Builder builder = new RequiredDependency.Builder();
        builder.setName(resource.getName());
        builder.setGroup(dependency.getGroup());
        builder.setList(dependency.getList());
        builder.setParameters(dependency.getParameters());
        builder.setProperties(dependency.getProperties());
        return builder.build();
    }

}
