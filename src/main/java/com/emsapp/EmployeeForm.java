package com.emsapp;

import com.emsapp.data.entity.Company;
import com.emsapp.data.entity.Employee;
import com.emsapp.data.entity.Status;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class EmployeeForm extends FormLayout {
  private Employee employee;

  TextField firstName = new TextField("First name");
  TextField lastName = new TextField("Last name");
  EmailField email = new EmailField("Email");
  ComboBox<Status> status = new ComboBox<>("Status");
  ComboBox<Company> company = new ComboBox<>("Company");
  Binder<Employee> binder = new BeanValidationBinder<>(Employee.class);

  Button save = new Button("Save");
  Button delete = new Button("Delete");
  Button close = new Button("Cancel");

  public EmployeeForm(List<Company> companies, List<Status> statuses) {
    addClassName("employee-form");
    binder.bindInstanceFields(this);

    company.setItems(companies);
    company.setItemLabelGenerator(Company::getName);
    status.setItems(statuses);
    status.setItemLabelGenerator(Status::getName);
    add(firstName,
        lastName,
        email,
        company,
        status,
        createButtonsLayout()); 
  }

  private HorizontalLayout createButtonsLayout() {
    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
    close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

    save.addClickShortcut(Key.ENTER);
    close.addClickShortcut(Key.ESCAPE);

    save.addClickListener(event -> validateAndSave());
    delete.addClickListener(event -> fireEvent(new DeleteEvent(this, employee)));
    close.addClickListener(event -> fireEvent(new CloseEvent(this)));


    binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

    return new HorizontalLayout(save, delete, close); 
  }

  public void setContact(Employee employee) {
    this.employee = employee;
    binder.readBean(employee);
  }

  private void validateAndSave() {
    try {
      binder.writeBean(employee);
      fireEvent(new SaveEvent(this, employee));
    } catch (ValidationException e) {
      e.printStackTrace();
    }
  }

  // Events
  public static abstract class ContactFormEvent extends ComponentEvent<EmployeeForm> {
    private Employee employee;

    protected ContactFormEvent(EmployeeForm source, Employee employee) {
      super(source, false);
      this.employee = employee;
    }

    public Employee getContact() {
      return employee;
    }
  }

  public static class SaveEvent extends ContactFormEvent {
    SaveEvent(EmployeeForm source, Employee employee) {
      super(source, employee);
    }
  }

  public static class DeleteEvent extends ContactFormEvent {
    DeleteEvent(EmployeeForm source, Employee employee) {
      super(source, employee);
    }

  }

  public static class CloseEvent extends ContactFormEvent {
    CloseEvent(EmployeeForm source) {
      super(source, null);
    }
  }

  public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                ComponentEventListener<T> listener) {
    return getEventBus().addListener(eventType, listener);
  }
}