import os
from dotenv import load_dotenv

load_dotenv()

class Settings:
    OPENAI_API_KEY = os.getenv("OPENAI_API_KEY")
    POSTGRES_URL = os.getenv("POSTGRES_URL")
    EMBEDDING_MODEL = os.getenv("EMBEDDING_MODEL")

settings = Settings()
