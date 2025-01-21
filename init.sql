-- Delete all data so duplicate data isn't inserted. Probably a better way to do this
DELETE * FROM accounting_fields;
DELETE * FROM accounting_field_values;

-- Insert into AccountingField
INSERT INTO accounting_fields (id, internal_id, field_name) VALUES (1,'af_0001','GL Code');
INSERT INTO accounting_fields (id, internal_id, field_name) VALUES (2,'af_0002','Department');

-- Insert into AccountingFieldValue
INSERT INTO accounting_field_values (id, internal_id, field_value, field_id) VALUES (1, 'afv_0001','100-001 Operating Cash', 1);
INSERT INTO accounting_field_values (id, internal_id, field_value, field_id) VALUES (2, 'afv_0002','100-002 Savings Accounts', 1);
INSERT INTO accounting_field_values (id, internal_id, field_value, field_id) VALUES (3, 'afv_0003','100-003 Money Market', 1);
INSERT INTO accounting_field_values (id, internal_id, field_value, field_id) VALUES (4, 'afv_0004','Corporate', 2);
INSERT INTO accounting_field_values (id, internal_id, field_value, field_id) VALUES (5, 'afv_0005','Sales', 2);

--Set Default Accounting Fields for banks
INSERT INTO bank_account_field_defaults (id, bank_account_id, accounting_field_id, accounting_field_value_id) VALUES (1, 1, 1,1);
INSERT INTO bank_account_field_defaults (id, bank_account_id, accounting_field_id, accounting_field_value_id) VALUES (2, 1, 2, 5);
INSERT INTO bank_account_field_defaults (id, bank_account_id, accounting_field_id, accounting_field_value_id) VALUES (3, 2, 1, 2);
INSERT INTO bank_account_field_defaults (id, bank_account_id, accounting_field_id, accounting_field_value_id) VALUES (4, 2, 2, 4);