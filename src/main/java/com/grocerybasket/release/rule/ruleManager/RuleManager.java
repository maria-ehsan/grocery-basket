package com.grocerybasket.release.rule.ruleManager;

import com.grocerybasket.release.models.ExcelProduct;
import com.grocerybasket.release.rule.ruleManager.Rule;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.support.composite.ActivationRuleGroup;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RuleManager {
    private final List<Rule> rules;

    public RuleManager(
            List<Rule> rules) {
        this.rules = rules;
    }

    public void run(List<ExcelProduct> excelProducts) {


        ActivationRuleGroup activationRuleGroup = new ActivationRuleGroup();
        rules.forEach(activationRuleGroup::addRule);

        Rules rules = new Rules();
        rules.register(activationRuleGroup);

        RulesEngine rulesEngine = new DefaultRulesEngine();

        Facts facts = new Facts();

        for(ExcelProduct excelProduct : excelProducts) {
            facts.put("excelProduct", excelProduct);
            rulesEngine.fire(rules, facts);
        }
    }

}
