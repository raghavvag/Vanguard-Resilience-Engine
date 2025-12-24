# Git Configuration Guide

## Overview
This project uses a comprehensive `.gitignore` strategy to prevent committing sensitive data, build artifacts, and IDE-specific files.

## Structure

```
.gitignore (root)          # Main gitignore for entire monorepo
backend/.gitignore         # Backend-specific ignores
frontend/.gitignore        # Frontend-specific ignores (to be added)
ml_service/.gitignore      # ML service-specific ignores (to be added)
```

## Protected Files

### Sensitive Configuration
- `application.properties` - Contains database passwords, API keys, JWT secrets
- `.env` files - Environment-specific secrets
- `*.key`, `*.pem`, `*.crt` - SSL/TLS certificates
- `secrets/` directory

### Build Artifacts
- `build/`, `target/`, `dist/` - Compiled code
- `node_modules/` - Node dependencies
- `__pycache__/`, `*.pyc` - Python bytecode
- `.gradle/` - Gradle cache

### IDE Files
- `.idea/` - IntelliJ IDEA
- `.vscode/` - Visual Studio Code
- `.settings/` - Eclipse

## Setup Instructions

### 1. Configure Application Properties
```bash
cd backend/src/main/resources
cp application.properties.example application.properties
# Edit application.properties with your actual credentials
```

### 2. Remove Already-Tracked Files
If you've already committed sensitive files:
```bash
# Remove from Git but keep locally
git rm --cached backend/src/main/resources/application.properties

# Commit the removal
git commit -m "Remove sensitive application.properties from tracking"
```

### 3. Verify Ignored Files
```bash
# Check what would be committed
git status

# Check if a specific file is ignored
git check-ignore -v backend/src/main/resources/application.properties
```

## Best Practices

1. **Never commit secrets** - Use environment variables or secret managers
2. **Use .example files** - Provide templates for configuration files
3. **Review before commit** - Always check `git status` before `git add .`
4. **Update .gitignore early** - Add patterns before committing files

## What's Ignored by Project Component

### Backend (Java/Spring Boot)
- Build artifacts: `build/`, `bin/`, `*.class`, `*.jar`
- Gradle: `.gradle/`, `gradle-app.setting`
- Configuration: `application.properties`
- IDE: `.idea/`, `*.iml`, `.vscode/`

### Frontend (Node.js)
- Dependencies: `node_modules/`
- Build: `dist/`, `build/`, `.next/`
- Logs: `npm-debug.log*`, `yarn-error.log*`

### ML Service (Python)
- Bytecode: `__pycache__/`, `*.pyc`
- Virtual env: `venv/`, `.venv/`
- Models: `*.h5`, `*.pkl`, `*.pt`
- Data: `data/raw/`, `data/processed/`

### Infrastructure
- Terraform: `.terraform/`, `*.tfstate`, `*.tfvars`
- Kubernetes secrets: `*-secret.yaml`
- Docker overrides: `docker-compose.override.yml`

## Troubleshooting

### File Still Tracked After Adding to .gitignore
```bash
# Remove from Git index
git rm -r --cached .

# Re-add everything (gitignore will be respected)
git add .

# Commit
git commit -m "Apply .gitignore rules"
```

### Check if File is Ignored
```bash
git check-ignore -v path/to/file
```

### List All Ignored Files
```bash
git status --ignored
```

## Security Reminders

⚠️ **NEVER commit:**
- API keys (OpenAI, AWS, etc.)
- Database passwords
- JWT secrets
- Private keys or certificates
- `.env` files with real credentials

✅ **ALWAYS commit:**
- `.example` template files
- Documentation
- Public configuration (without secrets)

