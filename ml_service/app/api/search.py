from fastapi import APIRouter
from app.services.embedding_service import generate_embedding
from app.services.vector_store import search_similar

router = APIRouter()

@router.get("/search/external-events")
def search_events(query: str):
    embedding = generate_embedding(query)
    results = search_similar(embedding)
    return [
        {"title": r[0], "description": r[1], "region": r[2]}
        for r in results
    ]
