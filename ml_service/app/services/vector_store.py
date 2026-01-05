from app.core.database import get_connection

def store_embedding(event, embedding):
    conn = get_connection()
    cur = conn.cursor()

    cur.execute("""
        INSERT INTO external_event_embeddings
        (event_type, title, description, region, embedding)
        VALUES (%s, %s, %s, %s, %s)
    """, (
        event.event_type,
        event.title,
        event.description,
        event.region,
        embedding
    ))

    conn.commit()
    cur.close()
    conn.close()


def search_similar(embedding, limit=5):
    conn = get_connection()
    cur = conn.cursor()

    cur.execute("""
        SELECT title, description, region
        FROM external_event_embeddings
        ORDER BY embedding <-> %s
        LIMIT %s
    """, (embedding, limit))

    rows = cur.fetchall()
    cur.close()
    conn.close()

    return rows
