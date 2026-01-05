from fastapi import APIRouter
from app.models.schemas import ExternalEventRequest
from app.services.embedding_service import generate_embedding
from app.services.vector_store import store_embedding

router = APIRouter()

@router.post("/embed/external-event")
def embed_external_event(event: ExternalEventRequest):
    text = f"{event.title}. {event.description}"
    embedding = generate_embedding(text)
    store_embedding(event, embedding)
    return {"status": "stored"}
