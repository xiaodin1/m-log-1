/**
 * 
 */
package org.mspring.msns.api.spider.service;

import java.util.List;

import org.mspring.msns.api.spider.vo.Rule;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;

/**
 * @author Gao Youbo
 * @since 2013-3-8
 * @description
 * @TODO
 */
public interface SpiderRuleService {
    Rule getRuleById(Long id);

    Rule createRule(Rule rule);

    void updateRule(Rule rule);

    void deleteRule(Long... id);

    Page<Rule> findRulePage(QueryCriterion queryCriterion, Page<Rule> rulePage);
    
    List<Rule> findAllEnabledRules();
}
