-- name: all-docusign-contexts
-- Returns all docusign contexts
SELECT *
FROM DOCUSIGN_CONTEXTS;

-- name: get-docusign-context-by-id
-- Fetches docusign context by id
SELECT *
FROM DOCUSIGN_CONTEXTS
WHERE id = :id;

-- name: insert-docusign-context<!
-- Creates docusign context
INSERT INTO DOCUSIGN_CONTEXTS ("id", "candidateId", "offerId") VALUES (:id, :candidateId, :offerId)