package com.basis.campina.xtarefas.config.containers;

import java.util.Objects;
import org.testcontainers.elasticsearch.ElasticsearchContainer;

public class ElasticSearchFactory {

    private static ElasticsearchContainer container;

    public static ElasticsearchContainer getInstance(){
        if(Objects.isNull(container)){
            container = new ElasticsearchContainer("docker.elastic.co/elasticsearch/elasticsearch:7.6.2");
            container.start();
        }
        return container;
    }

}
