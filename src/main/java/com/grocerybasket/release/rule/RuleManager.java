package com.grocerybasket.release.rule;

import com.grocerybasket.release.models.ExcelProduct;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.support.composite.ActivationRuleGroup;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RuleManager {
    private final List<RuleInterface> ruleInterfaces;

    public RuleManager(
            List<RuleInterface> ruleInterfaces) {
        this.ruleInterfaces = ruleInterfaces;
    }

    public void run(List<ExcelProduct> excelProducts) {
        Facts facts = new Facts();
        facts.put("product-list", excelProducts);

        ActivationRuleGroup activationRuleGroup = new ActivationRuleGroup();
        ruleInterfaces.forEach(activationRuleGroup::addRule);

        Rules rules = new Rules();
        rules.register(activationRuleGroup);

        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);
    }

}
