from fastapi import FastAPI
from app.api import embed, search

app = FastAPI(title="NEXUS-0 ML Service")

app.include_router(embed.router)
app.include_router(search.router)
