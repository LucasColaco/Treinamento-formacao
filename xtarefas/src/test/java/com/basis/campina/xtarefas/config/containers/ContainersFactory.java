package com.basis.campina.xtarefas.config.containers;

import java.util.Objects;
import org.testcontainers.elasticsearch.ElasticsearchContainer;

public class ContainersFactory {

    private static ContainersFactory containersFactory;

    private static ElasticsearchContainer elasticSearchFactory;

    public static ContainersFactory getInstance(){
        if(Objects.isNull(elasticSearchFactory)){
            elasticSearchFactory = ElasticSearchFactory.getInstance();
        }
        if(Objects.isNull(containersFactory)){
            containersFactory = new ContainersFactory();
        }
        return containersFactory;
    }
}
