-- =====================================================
-- 1. ROLES
-- =====================================================
INSERT INTO roles (name) VALUES ('ROLE_CUSTOMER');

-- =====================================================
-- 2. CUSTOMER (usuarios de prueba)
-- =====================================================
-- Password para todos: "password123"
-- Hash BCrypt: $2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhkm

INSERT INTO customer (username, email, password, role_id) 
VALUES ('juan.perez', 'juan.perez@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhkm', 
        (SELECT id_role FROM roles WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO customer (username, email, password, role_id) 
VALUES ('maria.garcia', 'maria.garcia@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhkm', 
        (SELECT id_role FROM roles WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO customer (username, email, password, role_id) 
VALUES ('carlos.lopez', 'carlos.lopez@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhkm', 
        (SELECT id_role FROM roles WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO customer (username, email, password, role_id) 
VALUES ('ana.martinez', 'ana.martinez@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhkm', 
        (SELECT id_role FROM roles WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO customer (username, email, password, role_id) 
VALUES ('luis.fernandez', 'luis.fernandez@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhkm', 
        (SELECT id_role FROM roles WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO customer (username, email, password, role_id) 
VALUES ('jose.lopez', 'jose.lopez@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhkm', 
        (SELECT id_role FROM roles WHERE name = 'ROLE_CUSTOMER'));

-- =====================================================
-- 3. PROFILE
-- =====================================================
INSERT INTO profile (avatar_image, dni, name, first_surname, second_surname, phone_number, customer_id)
VALUES
(NULL, '12345678A', 'Juan', 'Pérez', 'Gómez', '+34612345001', 
        (SELECT id FROM customer WHERE username = 'juan.perez')),

(NULL, '23456789B', 'María', 'García', 'López', '+34612345002', 
        (SELECT id FROM customer WHERE username = 'maria.garcia')),

(NULL, '34567890C', 'Carlos', 'López', 'Ruiz', '+34612345003', 
        (SELECT id FROM customer WHERE username = 'carlos.lopez')),

(NULL, '45678901D', 'Ana', 'Martínez', 'Santos', '+34612345004', 
        (SELECT id FROM customer WHERE username = 'ana.martinez')),

(NULL, '56789012E', 'Luis', 'Fernández', 'Moreno', '+34612345005', 
        (SELECT id FROM customer WHERE username = 'luis.fernandez')),

(NULL, '67890123F', 'José', 'López', 'Martín', '+34612345006', 
        (SELECT id FROM customer WHERE username = 'jose.lopez'));

-- =====================================================
-- 4. PRODUCTS (empanadas)
-- =====================================================
INSERT INTO products (name, description, price, image_url, available) 
VALUES ('Empanada de Pollo', 
        'Deliciosa empanada rellena de pollo desmenuzado con cebolla, pimiento y especias tradicionales.', 
        3.50, 
        'https://example.com/images/empanada-pollo.jpg', 
        true);

INSERT INTO products (name, description, price, image_url, available) 
VALUES ('Empanada de Carne', 
        'Jugosa empanada de carne picada con aceitunas, huevo duro y un toque de comino.', 
        3.80, 
        'https://example.com/images/empanada-carne.jpg', 
        true);

INSERT INTO products (name, description, price, image_url, available) 
VALUES ('Empanada de Atún', 
        'Fresca empanada rellena de atún, tomate, cebolla y pimientos asados.', 
        3.20, 
        'https://example.com/images/empanada-atun.jpg', 
        true);

INSERT INTO products (name, description, price, image_url, available) 
VALUES ('Empanada de Queso y Cebolla', 
        'Cremosa empanada de queso fundido con cebolla caramelizada.', 
        3.00, 
        'https://example.com/images/empanada-queso.jpg', 
        true);

INSERT INTO products (name, description, price, image_url, available) 
VALUES ('Empanada de Jamón y Queso', 
        'Clásica empanada con jamón york y queso mozzarella derretido.', 
        3.50, 
        'https://example.com/images/empanada-jamon-queso.jpg', 
        true);

INSERT INTO products (name, description, price, image_url, available) 
VALUES ('Empanada de Espinacas', 
        'Saludable empanada rellena de espinacas salteadas con ajo y queso ricotta.', 
        3.30, 
        NULL, 
        true);

INSERT INTO products (name, description, price, image_url, available) 
VALUES ('Empanada de Chorizo', 
        'Picante empanada rellena de chorizo ahumado con pimientos rojos.', 
        3.80, 
        NULL, 
        false);

-- =====================================================
-- 5. type_notifications
-- =====================================================
INSERT INTO type_notifications (name, description, status) 
VALUES ('PAYMENT_CONFIRMED', 
        'Tu pago ha sido confirmado. Tu pedido está en preparación.', 
        'Pago confirmado');

INSERT INTO type_notifications(name, description, status) 
VALUES ('PAYMENT_FAILED', 
        'El pago no pudo procesarse. Por favor, intenta nuevamente.', 
        'Pago fallido');

INSERT INTO type_notifications(name, description, status) 
VALUES ('ORDER_READY', 
        'Tu pedido está listo para retirar. Lugar en cola: {position}',
        'Listo para retirar');

INSERT INTO type_notifications (name, description, status) 
VALUES ('QUEUE_POSITION_UPDATE', 
        'Actualización de tu posición en la cola.', 
        'Posición en cola');

INSERT INTO type_notifications(name, description, status) 
VALUES ('TIME_REMINDER_10MIN', 
        'Faltan 10 minutos para retirar tu pedido.', 
        'Recordatorio 10min');

INSERT INTO type_notifications (name, description, status) 
VALUES ('TIME_REMINDER_5MIN', 
        'Faltan 5 minutos para retirar tu pedido.', 
        'Recordatorio 5min');

INSERT INTO type_notifications (name, description, status) 
VALUES ('TURN_NEXT', 
        'Tu turno es el próximo. Por favor, acércate al mostrador.', 
        'Turno próximo');

INSERT INTO type_notifications(name, description, status) 
VALUES ('TURN_EXPIRED', 
        'Tu turno ha expirado. Se te asignó un nuevo turno automáticamente.', 
        'Turno expirado');

INSERT INTO type_notifications (name, description, status) 
VALUES ('ORDER_COMPLETED', 
        '¡Gracias por tu compra! Esperamos verte pronto.', 
        'Pedido completado');

INSERT INTO type_notifications(name, description, status) 
VALUES ('ORDER_CANCELED', 
        'Tu pedido ha sido cancelado.', 
        'Pedido cancelado');