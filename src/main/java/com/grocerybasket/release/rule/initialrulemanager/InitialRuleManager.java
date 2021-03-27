package com.grocerybasket.release.rule.initialrulemanager;

import com.grocerybasket.release.models.ExcelProduct;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.support.composite.ActivationRuleGroup;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitialRuleManager {

    private final List<InitialRule> rules;

    public InitialRuleManager(
            List<InitialRule> rules) {
        this.rules = rules;
    }

    public void run(List<ExcelProduct> excelProducts) {


        ActivationRuleGroup activationRuleGroup = new ActivationRuleGroup();
        rules.forEach(activationRuleGroup::addRule);

        Rules rules = new Rules();
        rules.register(activationRuleGroup);

        RulesEngine rulesEngine = new DefaultRulesEngine();

        Facts facts = new Facts();
        facts.put("excelProducts", excelProducts);
        rulesEngine.fire(rules, facts);
    }
}
