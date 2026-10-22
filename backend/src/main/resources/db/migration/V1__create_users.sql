CREATE TABLE users (
    id UUID PRIMARY KEY,
    created_at TIMESTAMP(6) WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP(6) WITH TIME ZONE,
    deleted_at TIMESTAMP(6) WITH TIME ZONE,
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    microsoft_tenant_id UUID NOT NULL,
    microsoft_object_id UUID NOT NULL,
    display_name VARCHAR(255) NOT NULL,
    email VARCHAR(320),
    role VARCHAR(32) NOT NULL DEFAULT 'PARTICIPANT',
    blocked BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT uk_users_microsoft_identity
        UNIQUE (microsoft_tenant_id, microsoft_object_id),
    CONSTRAINT ck_users_role
        CHECK (role IN ('ADMIN', 'EXPERT', 'PARTICIPANT'))
);
