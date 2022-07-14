package com.github.quxiucheng.tutorial.rule;

import org.apache.calcite.rel.rules.CoreRules;
import org.apache.calcite.rel.rules.JoinAssociateRule;

/**
 * 它根据结合律规则更改连接
 * ((a JOIN b) JOIN c) → (a JOIN (b JOIN c))
 * @author quxiucheng
 * @date 2019-02-02 15:51:00
 * TODO:
 * IDEA:GOOD
 */
public class JoinAssociateRuleTest {
    public static void main(String[] args) {
        String sql = "select e.name as ename,d3.name as dname from hr.emps e " +
                "join hr.depts d1 on e.deptno = d1.deptno " +
                "join hr.depts d2 on e.deptno = d2.deptno " +
                "join hr.depts d3 on e.deptno = d3.deptno ";
        RuleTester.printOriginalCompare(sql, CoreRules.JOIN_ASSOCIATE);
        /**
         * sql:
         * select e.name as ename,d3.name as dname from hr.emps e join hr.depts d1 on e.deptno = d1.deptno join hr.depts d2 on e.deptno = d2.deptno join hr.depts d3 on e.deptno = d3.deptno
         *
         * 原始:
         * LogicalProject(ename=[$2], dname=[$12])
         *   LogicalJoin(condition=[=($1, $11)], joinType=[inner])
         *     LogicalJoin(condition=[=($1, $8)], joinType=[inner])
         *       LogicalJoin(condition=[=($1, $5)], joinType=[inner])
         *         LogicalTableScan(table=[[hr, emps]])
         *         LogicalTableScan(table=[[hr, depts]])
         *       LogicalTableScan(table=[[hr, depts]])
         *     LogicalTableScan(table=[[hr, depts]])
         *
         *
         * 优化后:
         * LogicalProject(ename=[$2], dname=[$12])
         *   LogicalJoin(condition=[=($1, $11)], joinType=[inner])
         *     LogicalJoin(condition=[=($1, $8)], joinType=[inner])
         *       LogicalJoin(condition=[=($1, $5)], joinType=[inner])
         *         LogicalTableScan(table=[[hr, emps]])
         *         LogicalTableScan(table=[[hr, depts]])
         *       LogicalTableScan(table=[[hr, depts]])
         *     LogicalTableScan(table=[[hr, depts]])
         *
         * SELECT `emps`.`name` AS `ename`, `depts1`.`name` AS `dname`
         * FROM `hr`.`emps`
         * INNER JOIN `hr`.`depts` ON `emps`.`deptno` = `depts`.`deptno`
         * INNER JOIN `hr`.`depts` AS `depts0` ON `emps`.`deptno` = `depts0`.`deptno`
         * INNER JOIN `hr`.`depts` AS `depts1` ON `emps`.`deptno` = `depts1`.`deptno`
         */

    }
}
