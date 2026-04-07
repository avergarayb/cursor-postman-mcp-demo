-- Usuarios
INSERT INTO users (name, email) VALUES ('Ana García', 'ana@example.com');
INSERT INTO users (name, email) VALUES ('Luis Pérez', 'luis@example.com');

-- Productos
INSERT INTO products (name, price) VALUES ('Teclado mecánico', 79.99);
INSERT INTO products (name, price) VALUES ('Ratón inalámbrico', 29.50);
INSERT INTO products (name, price) VALUES ('Monitor 27 pulgadas', 249.00);

-- Pedido de Ana (fecha fija para poder enlazar en pruebas)
INSERT INTO orders (order_date, user_id)
SELECT TIMESTAMP '2026-04-01 10:30:00', id FROM users WHERE email = 'ana@example.com';

-- Líneas del pedido
INSERT INTO order_items (quantity, order_id, product_id)
SELECT 1, o.id, p.id
FROM orders o
JOIN products p ON p.name = 'Teclado mecánico'
WHERE o.order_date = TIMESTAMP '2026-04-01 10:30:00';

INSERT INTO order_items (quantity, order_id, product_id)
SELECT 2, o.id, p.id
FROM orders o
JOIN products p ON p.name = 'Ratón inalámbrico'
WHERE o.order_date = TIMESTAMP '2026-04-01 10:30:00';
