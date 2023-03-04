package com.crmapp;

import com.crmapp.data.entity.Employee;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ListDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ListViewTest {

    @Autowired
    private ListView listView;

    @Test
    public void formShownWhenContactSelected() {
        Grid<Employee> grid = listView.grid;
        Employee firstEmployee = getFirstItem(grid);

        EmployeeForm form = listView.form;

        Assert.assertFalse(form.isVisible());
        grid.asSingleSelect().setValue(firstEmployee);
        Assert.assertTrue(form.isVisible());
        Assert.assertEquals(firstEmployee.getFirstName(), form.firstName.getValue());
    }
    private Employee getFirstItem(Grid<Employee> grid) {
        return( (ListDataProvider<Employee>) grid.getDataProvider()).getItems().iterator().next();
    }
}