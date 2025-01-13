-- Delete all data so duplicate data isn't inserted. Probably a better way to do this
DELETE * FROM accounting_fields;
DELETE * FROM accounting_field_values;

-- Insert into AccountingField
INSERT INTO accounting_fields (id, field_name) VALUES (1,'GL Code');
INSERT INTO accounting_fields (id, field_name) VALUES (2,'Department');

-- Insert into AccountingFieldValue
INSERT INTO accounting_field_values (id, field_value, field_id) VALUES (1, '100-001 Operating Cash', 1);
INSERT INTO accounting_field_values (id, field_value, field_id) VALUES (2, '100-002 Savings Accounts', 1);
INSERT INTO accounting_field_values (id, field_value, field_id) VALUES (3, '100-003 Money Market', 1);
INSERT INTO accounting_field_values (id, field_value, field_id) VALUES (4, 'Corporate', 2);
INSERT INTO accounting_field_values (id, field_value, field_id) VALUES (5, 'Sales', 2);

--Set Default Accounting Fields for banks
INSERT INTO bank_account_field_defaults (id, bank_account_id, accounting_field_id, accounting_field_value_id) VALUES (1, 1, 1,1);
INSERT INTO bank_account_field_defaults (id, bank_account_id, accounting_field_id, accounting_field_value_id) VALUES (2, 2, 1, 5);
INSERT INTO bank_account_field_defaults (id, bank_account_id, accounting_field_id, accounting_field_value_id) VALUES (3, 1, 2, 2);
INSERT INTO bank_account_field_defaults (id, bank_account_id, accounting_field_id, accounting_field_value_id) VALUES (4, 2, 2, 4);