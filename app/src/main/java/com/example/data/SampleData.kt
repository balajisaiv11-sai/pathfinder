package com.example.data

object SampleData {

  val defaultPlanSteps = listOf(
    PlanStep(
      id = "step-1",
      title = "List subjects and daily activities you naturally enjoy",
      description = "Reflect on topics where you lose track of time or like reading beyond the textbook.",
      isCompleted = true,
      targetDate = "This week",
      personalNote = "Enjoyed biology diagrams and practical science experiments in Class 10.",
      isPredefined = true
    ),
    PlanStep(
      id = "step-2",
      title = "Find real subject combinations offered by local schools or colleges",
      description = "Boards and institutions vary in stream combinations (e.g. PCM with Computer Science vs. PCB with Psychology).",
      isCompleted = false,
      targetDate = "Next 2 weeks",
      personalNote = null,
      isPredefined = true
    ),
    PlanStep(
      id = "step-3",
      title = "Check current eligibility and admission rules using official portals",
      description = "Always confirm state board, central board (CBSE/CISCE), or university criteria directly from official circulars.",
      isCompleted = false,
      targetDate = null,
      personalNote = null,
      isPredefined = true
    ),
    PlanStep(
      id = "step-4",
      title = "Compare course content, duration, costs, and practical lab work",
      description = "Evaluate travel distance, lab facilities, internship models, and estimated fees before narrowing choices.",
      isCompleted = false,
      targetDate = null,
      personalNote = null,
      isPredefined = true
    ),
    PlanStep(
      id = "step-5",
      title = "Ask whether government fee concessions or merit scholarships exist",
      description = "Explore state scholarship schemes, National Scholarship Portal (NSP), or institutional fee waivers.",
      isCompleted = false,
      targetDate = null,
      personalNote = null,
      isPredefined = true
    ),
    PlanStep(
      id = "step-6",
      title = "Talk with someone already studying or working along that route",
      description = "Ask a senior or family friend: What does an ordinary weekday look like? What surprised you?",
      isCompleted = false,
      targetDate = null,
      personalNote = null,
      isPredefined = true
    ),
    PlanStep(
      id = "step-7",
      title = "Select one primary direction to investigate and one calm backup option",
      description = "Having a backup keeps your mind steady and frees you from unnecessary pressure.",
      isCompleted = false,
      targetDate = null,
      personalNote = null,
      isPredefined = true
    )
  )

  val samplePathways = listOf(
    // After Class 10
    Pathway(
      id = "path-10-science",
      title = "Classes 11 & 12: Science Stream",
      stage = StudyStage.AFTER_10TH,
      category = "Academic Secondary",
      shortDescription = "Focuses on scientific methodology, physical principles, mathematics, and living organisms.",
      whoMayWantToExplore = "Students curious about understanding nature, solving quantitative problems, laboratory investigation, medicine, or engineering.",
      whatLearningInvolves = "Physics, Chemistry, with choices between Mathematics (PCM), Biology (PCB), Computer Science, or Physical Education.",
      questionsToCheck = listOf(
        "Does your school require specific minimum Class 10 marks for Science?",
        "Do you prefer practical lab experiments or theoretical mathematical problem solving?",
        "Are both PCM and PCMB combinations offered with supportive lab facilities?"
      ),
      sampleDirections = listOf("Engineering", "Medicine & Allied Health", "Data Science", "Biotechnology", "Pure Research")
    ),
    Pathway(
      id = "path-10-commerce",
      title = "Classes 11 & 12: Commerce Stream",
      stage = StudyStage.AFTER_10TH,
      category = "Academic Secondary",
      shortDescription = "Explores trade, finance, financial accounting, economic systems, and business organization.",
      whoMayWantToExplore = "Students fascinated by enterprise, how markets function, numbers, management, entrepreneurship, and commercial law.",
      whatLearningInvolves = "Accountancy, Business Studies, Economics, and an elective like Mathematics, Informatics Practices, or Applied Maths.",
      questionsToCheck = listOf(
        "Is Mathematics compulsory for your target college degrees later (e.g. B.Com Hons at top universities)?",
        "Do you enjoy balance sheets, economic news, and analyzing organizational case studies?",
        "What elective combinations (e.g. Entrepreneurship, IP) are available at your school?"
      ),
      sampleDirections = listOf("Chartered Accountancy", "Finance & Investment", "Corporate Law", "Business Analytics", "Economics")
    ),
    Pathway(
      id = "path-10-humanities",
      title = "Classes 11 & 12: Humanities & Arts",
      stage = StudyStage.AFTER_10TH,
      category = "Academic Secondary",
      shortDescription = "Examines human society, history, philosophical thought, culture, governance, and creative expression.",
      whoMayWantToExplore = "Students who enjoy deep reading, thoughtful writing, debating social questions, history, language, or psychology.",
      whatLearningInvolves = "History, Political Science, Sociology, Psychology, Geography, Literature, Economics, and Fine Arts.",
      questionsToCheck = listOf(
        "Can you combine humanities subjects with Economics or Mathematics?",
        "Does the school encourage research essays, debates, and critical reading projects?",
        "What language options and elective humanities subjects are scheduled?"
      ),
      sampleDirections = listOf("Public Policy & Civil Services", "Journalism & Media", "Psychology", "Design & Architecture", "Law")
    ),
    Pathway(
      id = "path-10-polytechnic",
      title = "Polytechnic Engineering & Technical Diplomas",
      stage = StudyStage.AFTER_10TH,
      category = "Technical Diploma",
      shortDescription = "3-year practical, industry-oriented engineering diplomas directly accessible after Class 10.",
      whoMayWantToExplore = "Students eager for hands-on technical training, machine workshops, electronics, civil drafting, or early engineering jobs.",
      whatLearningInvolves = "Intensive workshop practice, CAD drafting, circuit assembly, mechanical tooling, and industrial internships.",
      questionsToCheck = listOf(
        "Does your state offer lateral entry directly into 2nd year B.Tech after completing the 3-year diploma?",
        "Is the polytechnic institute approved by AICTE and state technical education boards?",
        "What campus placement or workshop apprenticeship linkages are active?"
      ),
      sampleDirections = listOf("Junior Engineer", "Lateral Entry B.Tech", "Production Supervisor", "Auto Tech Specialist")
    ),
    Pathway(
      id = "path-10-iti",
      title = "ITI (Industrial Training Institutes) & Crafts",
      stage = StudyStage.AFTER_10TH,
      category = "Trade & Craft Training",
      shortDescription = "1 to 2-year dedicated vocational craft courses under National Council for Vocational Training (NCVT).",
      whoMayWantToExplore = "Students who prefer tactile, practical skilled trades like electrical wiring, precision machining, welding, or refrigeration.",
      whatLearningInvolves = "Over 70% practical shop-floor training, safety standards, blueprint reading, and trade certificate exams.",
      questionsToCheck = listOf(
        "Is the trade certified under NCVT (National) or SCVT (State)?",
        "Does the institute facilitate National Apprenticeship Certificate (NAC) training with local industries?",
        "Can ITI graduates pursue 2nd year polytechnic diplomas through lateral entry?"
      ),
      sampleDirections = listOf("Industrial Electrician", "Precision Machinist", "Mechatronics Technician", "Solar Installer")
    ),
    Pathway(
      id = "path-10-open",
      title = "Recognised Open & Flexible Schooling (NIOS)",
      stage = StudyStage.AFTER_10TH,
      category = "Flexible Schooling",
      shortDescription = "Self-paced study programs under the National Institute of Open Schooling or State Open Schools.",
      whoMayWantToExplore = "Athletes, creative artists, working students, or learners seeking flexible subject combinations and self-paced timelines.",
      whatLearningInvolves = "Self-instructional course materials, personal contact classes, online tutor marked assignments, and On-Demand exams.",
      questionsToCheck = listOf(
        "Are NIOS Senior Secondary certificates recognized by your target university and national entrance tests (NEET/JEE/CUET)? (Yes, NIOS is recognized by AIU and MHRD).",
        "Do you have the discipline for self-study and meeting assignment deadlines?",
        "Are practical examination study centres easily reachable in your district?"
      ),
      sampleDirections = listOf("Flexible Higher Studies", "Sports & Arts Career", "Civil Exams", "Professional Training")
    ),

    // After Intermediate / Class 12
    Pathway(
      id = "path-12-ug-general",
      title = "Undergraduate Degrees (B.A., B.Sc., B.Com., BCA)",
      stage = StudyStage.AFTER_12TH,
      category = "Higher Education",
      shortDescription = "3 to 4-year foundational university bachelor degrees offering in-depth subject knowledge and analytical training.",
      whoMayWantToExplore = "Students seeking strong conceptual grounding, postgraduate eligibility, competitive examinations, or corporate recruitment.",
      whatLearningInvolves = "Core major subjects, interdisciplinary minors, research dissertations, seminars, and credit-based university coursework.",
      questionsToCheck = listOf(
        "Is the programme structured under the National Education Policy (NEP 2020) 4-year undergraduate framework?",
        "Does the college require the Common University Entrance Test (CUET) or merit-based board marks?",
        "Are there opportunities for student exchange, internships, or dual-major minors?"
      ),
      sampleDirections = listOf("Research & Academia", "Corporate Management", "Public Administration", "Software Development")
    ),
    Pathway(
      id = "path-12-professional",
      title = "Professional Degrees (B.Tech, MBBS, B.Arch, Law)",
      stage = StudyStage.AFTER_12TH,
      category = "Regulated Professional",
      shortDescription = "Rigorous 4 to 5.5-year degrees accredited by statutory bodies (AICTE, NMC, BCI, COA).",
      whoMayWantToExplore = "Students committed to licensed professions like clinical medicine, engineering, architectural practice, or legal advocacy.",
      whatLearningInvolves = "Clinical rotations, capstone engineering projects, moot court competitions, architectural studio juries, and mandatory licensing exams.",
      questionsToCheck = listOf(
        "What national or state entrance test is required (JEE Main/Advanced, NEET-UG, CLAT, NATA)?",
        "What is the duration including mandatory internships or house surgeoncy?",
        "Are the institution and its hospital/labs accredited by official national regulatory councils?"
      ),
      sampleDirections = listOf("Licensed Physician", "Software Architect", "Advocate & Counsel", "Registered Architect")
    ),
    Pathway(
      id = "path-12-vocational-diploma",
      title = "Paramedical, Design & Specialized Diplomas",
      stage = StudyStage.AFTER_12TH,
      category = "Vocational & Applied",
      shortDescription = "1 to 3-year specialized training programs in fields like medical laboratory technology, interior styling, or pharmacy.",
      whoMayWantToExplore = "Students looking for career-specific practical skills without committing to a lengthy 4-5 year academic degree.",
      whatLearningInvolves = "Hospital laboratory training, digital design suites, practical diagnostic instruments, and supervised clinic hours.",
      questionsToCheck = listOf(
        "Is the diploma recognized by the state paramedical council or Pharmacy Council of India (PCI)?",
        "Does the training include hands-on hospital or agency internships?",
        "Can credits be transferred toward a B.Voc or Bachelor degree later?"
      ),
      sampleDirections = listOf("Medical Lab Technologist", "Graphic & UI Designer", "Hospitality Executive", "Retail Manager")
    ),
    Pathway(
      id = "path-12-apprenticeship",
      title = "Apprenticeships & Skill India Training (NATS / NAPS)",
      stage = StudyStage.AFTER_12TH,
      category = "Work-Integrated Learning",
      shortDescription = "Paid, government-stipended industrial training schemes bridging theoretical knowledge and direct workplace competence.",
      whoMayWantToExplore = "Students wanting to earn while they learn and build verifiable industry experience in manufacturing, banking, or logistics.",
      whatLearningInvolves = "On-the-job industrial shifts under experienced mentors, monthly stipend, workplace assessments, and National Apprenticeship certification.",
      questionsToCheck = listOf(
        "Is the contract registered on the official National Apprenticeship Promotion Scheme (NAPS) portal?",
        "What is the monthly stipend and training duration (typically 12–24 months)?",
        "Does the host organization offer permanent absorption for top trainees?"
      ),
      sampleDirections = listOf("Certified Apprentice", "Technician Specialist", "Logistics Supervisor", "Operations Associate")
    ),
    Pathway(
      id = "path-12-distance-work",
      title = "Distance Education & Work-While-Studying",
      stage = StudyStage.AFTER_12TH,
      category = "Flexible Distance",
      shortDescription = "Study for accredited degrees (e.g., IGNOU, state open universities) while working or pursuing vocational training.",
      whoMayWantToExplore = "Students supporting family responsibilities, gaining early work experience, or preparing for long-term competitive exams.",
      whatLearningInvolves = "Printed/digital self-learning material, weekend regional study centre counselling, online lectures, and term-end examinations.",
      questionsToCheck = listOf(
        "Is the open/distance degree approved by the UGC Distance Education Bureau (UGC-DEB)?",
        "Are examination centres convenient to your city or workplace?",
        "How will you manage time between weekday work and weekend assignment writing?"
      ),
      sampleDirections = listOf("Working Professional", "Civil Services Aspirant", "Independent Freelancer", "Family Business Manager")
    )
  )

  val sampleCareers = listOf(
    Career(
      id = "car-data-analyst",
      title = "Data Analyst & Insight Specialist",
      field = "Technology & Analytics",
      overview = "Examines trends, patterns, and numbers to help organizations, schools, or public health bodies make sensible decisions.",
      relevantSkills = listOf("Analytical curiosity", "Basic Python/SQL", "Spreadsheets", "Visual storytelling", "Critical questioning"),
      whyItMayInterestYou = "You mentioned enjoying problem solving and working through structured logic.",
      possibleRoutes = listOf("Class 12 (Maths/Stats/Computer Science)", "B.Sc Statistics / Data Science / BCA", "Skill certifications & portfolio projects"),
      workInvolves = listOf(
        "Cleaning messy datasets from surveys, user logs, or agricultural sensors",
        "Building clear dashboards and charts that non-technical people understand",
        "Translating raw figures into practical recommendations"
      ),
      thingsToInvestigate = listOf(
        "Look at free open datasets on data.gov.in to see what civic data looks like",
        "Experiment with basic spreadsheets (formulas, pivot tables, chart design)",
        "Read about how data science is used in Indian agriculture and railway logistics"
      ),
      relatedOptions = listOf("Software Developer", "Business Analyst", "Bioinformatics Researcher"),
      stageRelevance = StudyStage.BOTH,
      tags = listOf("problem solving", "logic", "numbers", "technology", "computers")
    ),
    Career(
      id = "car-ayush-physiotherapy",
      title = "Physiotherapist & Rehabilitation Specialist",
      field = "Healthcare & Life Sciences",
      overview = "Assists patients recovering from sports injuries, strokes, orthopedic surgery, or chronic joint pains to regain mobility and strength.",
      relevantSkills = listOf("Empathy & patience", "Anatomy understanding", "Gentle physical stamina", "Clear communication", "Observation"),
      whyItMayInterestYou = "You expressed an interest in directly helping people recover and working in hands-on clinical care.",
      possibleRoutes = listOf("Class 12 with PCB (Physics, Chemistry, Biology)", "Bachelor of Physiotherapy (BPT - 4.5 years)", "Hospital clinical internship"),
      workInvolves = listOf(
        "Evaluating joint movement, muscular reflexes, and pain triggers",
        "Guiding restorative exercise therapies, ultrasound, and mobility routines",
        "Educating patients on posture, ergonomics, and daily pain prevention"
      ),
      thingsToInvestigate = listOf(
        "Observe or ask a local physiotherapy clinic about their routine caseload",
        "Check BPT colleges affiliated with state medical universities and their hospital beds",
        "Understand the licensing and registration under the National Commission for Allied and Healthcare Professions"
      ),
      relatedOptions = listOf("Occupational Therapist", "Clinical Nurse", "Sports Nutritionist"),
      stageRelevance = StudyStage.AFTER_12TH,
      tags = listOf("helping people", "healthcare", "biology", "hands-on", "patient care")
    ),
    Career(
      id = "car-agri-horticulture",
      title = "Agricultural Officer & Sustainable Farm Advisor",
      field = "Agriculture & Environment",
      overview = "Works with farmers, cooperatives, and seed institutes to improve soil health, crop yields, water-saving irrigation, and organic pest management.",
      relevantSkills = listOf("Plant science", "Field observation", "Patience with seasonal cycles", "Rural communication", "Environmental care"),
      whyItMayInterestYou = "You enjoy being outdoors, working with living systems, and supporting sustainable rural communities.",
      possibleRoutes = listOf("Class 12 with Science (PCB or PCM or Agriculture)", "B.Sc (Hons) Agriculture / Horticulture (4 years via ICAR or state agri entrance)", "State Agri Extension Officer exams or private agri-tech"),
      workInvolves = listOf(
        "Testing soil nutrient balances and prescribing eco-friendly bio-fertilizers",
        "Advising farming communities on climate-resilient seeds and drip irrigation",
        "Working on farm experiments and post-harvest storage techniques"
      ),
      thingsToInvestigate = listOf(
        "Visit a nearby Krishi Vigyan Kendra (KVK) to see real agricultural extension work",
        "Check ICAR AIEEA entrance structure and state agricultural universities",
        "Explore how IoT sensors and drone surveys are changing modern farming"
      ),
      relatedOptions = listOf("Soil Scientist", "Forest Range Officer", "Horticulturist"),
      stageRelevance = StudyStage.AFTER_12TH,
      tags = listOf("nature", "environment", "agriculture", "outdoors", "practical")
    ),
    Career(
      id = "car-ui-ux-designer",
      title = "UI/UX & Product Designer",
      field = "Creative & Design",
      overview = "Creates clear, accessible mobile apps, websites, and digital tools that people of all literacy levels can navigate effortlessly.",
      relevantSkills = listOf("Visual empathy", "Wireframing & sketching", "Listening to user pain points", "Typography & spacing", "Prototyping"),
      whyItMayInterestYou = "You enjoy visual creativity, sketching, and thinking about how everyday objects and apps can be made simpler.",
      possibleRoutes = listOf("Any Class 12 stream", "B.Des (via UCEED / NID DAT) or self-taught portfolio alongside B.A./B.Sc/B.Tech", "App design internships"),
      workInvolves = listOf(
        "Conducting brief user interviews to learn where people get confused in an app",
        "Creating paper sketches, visual screen layouts, and interactive tap prototypes",
        "Collaborating with software developers to ensure designs look crisp on all screens"
      ),
      thingsToInvestigate = listOf(
        "Try wireframing a simplified railway booking or local bus pass screen on paper",
        "Explore the free design guidelines on Material Design (m3.material.io)",
        "Review sample question papers for UCEED and NID entrance examinations"
      ),
      relatedOptions = listOf("Graphic Designer", "Illustrator", "Industrial Product Designer"),
      stageRelevance = StudyStage.BOTH,
      tags = listOf("art", "design", "creative", "visual", "apps")
    ),
    Career(
      id = "car-chartered-accountant",
      title = "Chartered Accountant & Financial Strategist",
      field = "Finance & Business",
      overview = "Audits financial statements, plans tax compliance, investigates financial records, and advises businesses on sensible capital allocation.",
      relevantSkills = listOf("Attention to detail", "Ethical rigor", "Numerical discipline", "Commercial acumen", "Taxation laws"),
      whyItMayInterestYou = "You enjoy structured calculations, financial news, and understanding how businesses stay sustainable.",
      possibleRoutes = listOf("Class 12 (Commerce or any stream)", "ICAI CA Foundation -> CA Intermediate -> 2-3 years Articleship -> CA Final", "Optional parallel B.Com degree"),
      workInvolves = listOf(
        "Examining company records to ensure transparency and statutory tax compliance",
        "Advising startups, family enterprises, and NGOs on budgeting and cash flow",
        "Representing clients before regulatory authorities and preparing audit reports"
      ),
      thingsToInvestigate = listOf(
        "Read about the ICAI syllabus and the structure of practical articleship training",
        "Talk with a practising CA about how their working hours vary around tax deadlines",
        "Explore basic double-entry bookkeeping concepts in Class 11 Accountancy"
      ),
      relatedOptions = listOf("Cost & Management Accountant (CMA)", "Company Secretary (CS)", "Investment Analyst"),
      stageRelevance = StudyStage.BOTH,
      tags = listOf("business", "finance", "numbers", "problem solving", "rules")
    ),
    Career(
      id = "car-electrician-technician",
      title = "Electrical Systems & Solar Technician",
      field = "Skilled Crafts & Trades",
      overview = "Installs, tests, maintains, and repairs electrical wiring, solar panel inverters, industrial motors, and electrical safety systems.",
      relevantSkills = listOf("Hands-on dexterity", "Safety protocol adherence", "Circuit diagram reading", "Diagnostic troubleshooting", "Physical agility"),
      whyItMayInterestYou = "You enjoy tangible, practical work with tools and learning how physical machinery and renewable energy function.",
      possibleRoutes = listOf("Class 10 completion", "2-year ITI Electrician course or 3-year Electrical Polytechnic Diploma", "State Electrical Supervisor License & Apprenticeship"),
      workInvolves = listOf(
        "Reading electrical engineering blueprints and tracing circuit continuity",
        "Installing residential rooftop solar arrays, batteries, and safety breakers",
        "Troubleshooting motor control panels and performing periodic maintenance"
      ),
      thingsToInvestigate = listOf(
        "Visit a local ITI electrical lab to observe transformer and motor bench tests",
        "Check rooftop solar installation training under the Suryamitra skill initiative",
        "Learn about state licensing requirements for independent electrical contractors"
      ),
      relatedOptions = listOf("Mechatronics Specialist", "HVAC Technician", "Electronics Assembly Lead"),
      stageRelevance = StudyStage.AFTER_10TH,
      tags = listOf("hands-on", "tools", "practical", "solar", "mechanics")
    ),
    Career(
      id = "car-legal-advocate",
      title = "Legal Counsel & Dispute Resolution Advocate",
      field = "Law & Public Policy",
      overview = "Represents citizens, companies, or public interests in courts, drafts contracts, interprets constitutional rights, and negotiates settlements.",
      relevantSkills = listOf("Clear analytical thinking", "Precise drafting", "Persuasive speaking", "Ethical judgment", "Deep reading"),
      whyItMayInterestYou = "You care about justice, constitutional rights, resolving conflicts, and reading case precedents.",
      possibleRoutes = listOf("Class 12 (Any stream)", "5-year Integrated B.A. LL.B / B.B.A. LL.B (via CLAT/AILET) or 3-year LL.B after graduation", "Bar Council of India enrollment"),
      workInvolves = listOf(
        "Interviewing clients to understand the facts and evidence of a legal grievance",
        "Researching relevant statutes, judicial precedents, and case laws",
        "Drafting petitions, contracts, or consumer complaints and arguing in court"
      ),
      thingsToInvestigate = listOf(
        "Read a published landmark judgment by the Supreme Court or High Court",
        "Watch an online mock trial or college moot court competition",
        "Check the five-year integrated law entrance structure (CLAT / state CETs)"
      ),
      relatedOptions = listOf("Corporate Compliance Officer", "Human Rights Researcher", "Arbitrator"),
      stageRelevance = StudyStage.AFTER_12TH,
      tags = listOf("law", "helping people", "reading", "debate", "public service")
    ),
    Career(
      id = "car-school-educator",
      title = "Teacher & Experiential Educator",
      field = "Education & Social Science",
      overview = "Designs engaging classroom lessons, mentors students, demystifies complex subjects, and sparks long-term intellectual curiosity.",
      relevantSkills = listOf("Patience & active listening", "Subject enthusiasm", "Creative lesson design", "Classroom encouragement", "Assessment reflection"),
      whyItMayInterestYou = "You find joy in explaining ideas clearly, encouraging younger students, and building community.",
      possibleRoutes = listOf("Class 12 (Any stream)", "4-year Integrated B.Sc.B.Ed or B.A.B.Ed / D.El.Ed / B.Ed after graduation", "Teacher Eligibility Test (TET/CTET)"),
      workInvolves = listOf(
        "Planning interactive activities, experiments, and discussions for the classroom",
        "Giving individualized feedback to students with different learning styles",
        "Organizing school exhibitions, science fairs, and reading circles"
      ),
      thingsToInvestigate = listOf(
        "Try explaining a difficult textbook concept to a younger sibling or friend",
        "Explore how the National Education Policy (NEP) emphasizes experiential learning",
        "Check integrated teacher education programmes (ITEP) in central universities"
      ),
      relatedOptions = listOf("Special Educator", "Educational Content Creator", "Curriculum Specialist"),
      stageRelevance = StudyStage.BOTH,
      tags = listOf("helping people", "education", "communication", "teaching", "community")
    ),
    Career(
      id = "car-clinical-psychologist",
      title = "Counselor & Mental Health Practitioner",
      field = "Psychology & Behavioral Health",
      overview = "Provides confidential emotional support, counseling, and evidence-based interventions to help people manage anxiety, grief, and life transitions.",
      relevantSkills = listOf("Deep non-judgmental empathy", "Emotional composure", "Active listening", "Scientific ethics", "Insightful observation"),
      whyItMayInterestYou = "You are drawn to understanding human emotions, behavior, and providing a safe space for people in distress.",
      possibleRoutes = listOf("Class 12 (Any stream, Humanities/Science preferred)", "B.A. / B.Sc Psychology (3–4 years)", "M.A./M.Sc Psychology followed by M.Phil / RCI licensing for clinical practice"),
      workInvolves = listOf(
        "Conducting structured counseling sessions with students, families, or adults",
        "Administering standardized psychometric tests and behavioral assessments",
        "Designing mental wellbeing workshops for schools and workplaces"
      ),
      thingsToInvestigate = listOf(
        "Read introductory articles on cognitive behavioral therapy and active listening",
        "Understand the distinction between a Psychiatrist (medical doctor) and a Psychologist",
        "Review the Rehabilitation Council of India (RCI) guidelines for clinical practice"
      ),
      relatedOptions = listOf("School Counselor", "Organizational Psychologist", "Social Worker"),
      stageRelevance = StudyStage.AFTER_12TH,
      tags = listOf("helping people", "psychology", "listening", "empathy", "mental health")
    ),
    Career(
      id = "car-environmental-scientist",
      title = "Environmental Scientist & Water Resource Planner",
      field = "Agriculture & Environment",
      overview = "Monitors water bodies, air quality, forest ecosystems, and urban waste systems to prevent pollution and protect biodiversity.",
      relevantSkills = listOf("Ecological fieldwork", "Water testing & chemistry", "Data logging", "Environmental laws", "Community advocacy"),
      whyItMayInterestYou = "You care deeply about rivers, clean air, wildlife habitats, and practical ecological preservation.",
      possibleRoutes = listOf("Class 12 with Science (PCB / PCM)", "B.Sc Environmental Science / Geology / Chemistry", "M.Sc & environmental impact assessment roles"),
      workInvolves = listOf(
        "Collecting water, soil, and air samples from industrial zones and rivers",
        "Analyzing chemical contaminant levels against Central Pollution Control Board (CPCB) norms",
        "Preparing Environmental Impact Assessment (EIA) reports for local projects"
      ),
      thingsToInvestigate = listOf(
        "Check real-time air quality index (AQI) bulletins from the National AQI portal",
        "Read about local river restoration or watershed management projects in your state",
        "Review universities offering comprehensive B.Sc Environmental Science degrees"
      ),
      relatedOptions = listOf("Wildlife Biologist", "Urban Planner", "Sustainability Consultant"),
      stageRelevance = StudyStage.AFTER_12TH,
      tags = listOf("nature", "environment", "science", "ecology", "outdoors")
    ),
    Career(
      id = "car-civil-services",
      title = "Public Policy & Administrative Officer",
      field = "Public Service & Governance",
      overview = "Administers district public services, implements welfare programs, manages disaster response, and oversees local revenue administration.",
      relevantSkills = listOf("General awareness", "Integrity & ethical leadership", "Decision making under pressure", "Public service dedication", "Multilingual communication"),
      whyItMayInterestYou = "You want to serve the public directly, shape policy implementation, and impact grassroots development.",
      possibleRoutes = listOf("Class 12 (Any stream)", "Any Bachelor degree (B.A., B.Sc., B.Com., B.Tech, etc.)", "Union Public Service Commission (UPSC CSE) or State Public Service Commission (State PSC) exams"),
      workInvolves = listOf(
        "Monitoring public healthcare delivery, ration distribution, and rural road building",
        "Coordinating relief efforts during floods, heatwaves, or local emergencies",
        "Conducting public hearings to resolve grievances from citizens"
      ),
      thingsToInvestigate = listOf(
        "Read the daily editorial pages of a reputable national newspaper",
        "Understand the multi-stage format of UPSC CSE (Prelims, Mains, Personality Test)",
        "Learn how district administration functions through the collectorate"
      ),
      relatedOptions = listOf("Policy Researcher", "Rural Development Specialist", "Diplomatic Officer"),
      stageRelevance = StudyStage.AFTER_12TH,
      tags = listOf("public service", "governance", "helping people", "reading", "leadership")
    ),
    Career(
      id = "car-multimedia-journalist",
      title = "Investigative Journalist & Digital Storyteller",
      field = "Media & Communication",
      overview = "Investigates public occurrences, interviews witnesses, fact-checks information, and communicates news through articles, podcasts, or video reports.",
      relevantSkills = listOf("Curious persistence", "Fact-checking", "Speedy clear writing", "Audio/video recording", "Interviewing courage"),
      whyItMayInterestYou = "You love uncovering facts, asking bold questions, and telling compelling true stories.",
      possibleRoutes = listOf("Class 12 (Any stream)", "B.A. Journalism & Mass Communication (BJMC) or English / Regional Language literature", "Newsroom reporting internships"),
      workInvolves = listOf(
        "Verifying claims, official press releases, and social media videos for accuracy",
        "Traveling to locations to interview community members and local authorities",
        "Writing balanced news stories under real-time editorial deadlines"
      ),
      thingsToInvestigate = listOf(
        "Follow independent fact-checking collectives in India and study their methods",
        "Create a short video or written photo-essay on a local artisan or market in your town",
        "Explore curricula of premier journalism institutes like IIMC and ACJ"
      ),
      relatedOptions = listOf("Documentary Filmmaker", "Podcast Producer", "Public Relations Specialist"),
      stageRelevance = StudyStage.BOTH,
      tags = listOf("creative", "writing", "communication", "investigation", "media")
    )
  )

  val sampleCourses = listOf(
    Course(
      id = "crs-polytechnic-mech",
      title = "Diploma in Mechanical Engineering",
      area = "Polytechnic & Technical Trades",
      stage = StudyStage.AFTER_10TH,
      courseType = "Technical Diploma",
      approxDuration = "3 years (approximate; varies by state board)",
      subjectsOrSkills = listOf("Workshop practice", "Applied mechanics", "CAD drafting", "Thermal engineering", "Machine maintenance"),
      relatedCareers = listOf("Junior Mechanical Engineer", "Production Supervisor", "Automotive Technician"),
      personalizationNote = "Relevant for students who enjoy tactile mechanics, machinery, and early practical engineering.",
      whatStudentsStudy = listOf(
        "Engineering drawing, machine elements, and geometric dimensioning",
        "Lathe, milling, CNC machining, welding, and foundry operations",
        "Thermodynamics, fluid mechanics, and pneumatics"
      ),
      skillsPractised = listOf("Workshop tool safety", "Blueprint reading", "Basic CAD software", "Quality control inspection"),
      furtherStudy = listOf("Direct 2nd year lateral entry into B.Tech (Mechanical / Manufacturing)", "Advanced automation diplomas"),
      questionsToAsk = listOf(
        "How many working machines and CNC units are available per student batch?",
        "Does the polytechnic have campus interview linkages with local manufacturing units?"
      ),
      whatToVerify = listOf("State Technical Board recognition and AICTE approval status.")
    ),
    Course(
      id = "crs-iti-electrician",
      title = "Craftsman Certificate: ITI Electrician",
      area = "ITI & Trade Training",
      stage = StudyStage.AFTER_10TH,
      courseType = "Trade Certificate",
      approxDuration = "2 years (varies by trade exam schedule)",
      subjectsOrSkills = listOf("Domestic & industrial wiring", "Motor rewinding", "Safety earthing", "Inverters & batteries", "Appliance repair"),
      relatedCareers = listOf("Industrial Electrician", "Solar Installation Technician", "Power Substation Assistant"),
      personalizationNote = "A respected, hands-on path for students wanting immediate practical skills and contractor licensing.",
      whatStudentsStudy = listOf(
        "Ohm's Law, alternating current circuits, single & three-phase systems",
        "Transformer operation, induction motors, and DC generators",
        "Safety regulations, fire prevention, and first aid on electrical worksites"
      ),
      skillsPractised = listOf("Conduit wiring installation", "Multimeter & megger testing", "Motor troubleshooting", "Solar inverter connection"),
      furtherStudy = listOf("National Apprenticeship Certificate (NAC - 1 year)", "Lateral entry into 2nd year Polytechnic Electrical Diploma"),
      questionsToAsk = listOf(
        "Is this course affiliated under NCVT (CTS scheme)?",
        "Does the ITI facilitate on-the-job apprenticeship with state power utilities or private factories?"
      ),
      whatToVerify = listOf("Affiliation code on the official NCVT MIS portal (ncvtmis.gov.in).")
    ),
    Course(
      id = "crs-inter-mpc",
      title = "Intermediate / Class 11-12: Science (PCM)",
      area = "Secondary Schooling",
      stage = StudyStage.AFTER_10TH,
      courseType = "Academic Higher Secondary",
      approxDuration = "2 years (Classes 11 & 12)",
      subjectsOrSkills = listOf("Physics", "Chemistry", "Mathematics", "English", "Elective (Computer Science / Physical Ed)"),
      relatedCareers = listOf("Software Developer", "Data Analyst", "Civil Engineer", "Commercial Pilot", "Architect"),
      personalizationNote = "Core foundation for quantitative sciences, engineering, architecture, and technology degrees.",
      whatStudentsStudy = listOf(
        "Calculus, algebra, vectors, coordinate geometry, and probability",
        "Mechanics, electromagnetism, optics, modern physics, and practical experiments",
        "Physical, organic, and inorganic chemistry reactions and lab titrations"
      ),
      skillsPractised = listOf("Mathematical problem-solving", "Laboratory report writing", "Analytical logic"),
      furtherStudy = listOf("B.Tech / B.E.", "B.Sc Mathematics / Statistics / Physics", "B.Arch", "BCA / Data Science"),
      questionsToAsk = listOf(
        "Are laboratory classes conducted weekly with individual apparatus?",
        "Does the school offer support for foundation entrance preparation?"
      ),
      whatToVerify = listOf("Board affiliation (CBSE, CISCE, or State Board of Intermediate Education).")
    ),
    Course(
      id = "crs-inter-bipc",
      title = "Intermediate / Class 11-12: Science (PCB)",
      area = "Secondary Schooling",
      stage = StudyStage.AFTER_10TH,
      courseType = "Academic Higher Secondary",
      approxDuration = "2 years (Classes 11 & 12)",
      subjectsOrSkills = listOf("Physics", "Chemistry", "Biology (Botany & Zoology)", "English", "Language/Elective"),
      relatedCareers = listOf("Physiotherapist", "Doctor (MBBS/BAMS)", "Biotechnologist", "Pharmacist", "Agricultural Scientist"),
      personalizationNote = "Designed for learners fascinated by living organisms, cellular biology, human health, and genetics.",
      whatStudentsStudy = listOf(
        "Plant physiology, animal anatomy, ecology, genetics, and cell biology",
        "Chemical bonding, organic synthesis, biochemistry, and thermodynamics",
        "Physics of sound, waves, optical lenses, and basic instrumentation"
      ),
      skillsPractised = listOf("Microscope specimen preparation", "Biological illustration", "Scientific classification"),
      furtherStudy = listOf("NEET-UG for MBBS/BDS/AYUSH", "B.Sc Biotechnology / Nursing / Agriculture", "B.Pharm", "Bachelor of Physiotherapy"),
      questionsToAsk = listOf(
        "Are biology specimen dissecting trays and microscopes readily accessible?",
        "Can a student take Mathematics as an additional subject (PCMB) if desired?"
      ),
      whatToVerify = listOf("Specific subject codes required by future professional councils (e.g. NMC requires regular PCB).")
    ),
    Course(
      id = "crs-inter-cec",
      title = "Intermediate / Class 11-12: Commerce & Economics",
      area = "Secondary Schooling",
      stage = StudyStage.AFTER_10TH,
      courseType = "Academic Higher Secondary",
      approxDuration = "2 years (Classes 11 & 12)",
      subjectsOrSkills = listOf("Accountancy", "Business Studies", "Economics", "Mathematics or Informatics Practices"),
      relatedCareers = listOf("Chartered Accountant", "Financial Analyst", "Company Secretary", "Corporate Lawyer", "Banker"),
      personalizationNote = "Builds commercial intuition, ledger discipline, and an understanding of economic markets.",
      whatStudentsStudy = listOf(
        "Financial statements of sole proprietorships and partnership firms",
        "Microeconomics, macroeconomics, national income, and public finance",
        "Business management principles, marketing, staffing, and consumer protection"
      ),
      skillsPractised = listOf("Balancing journal ledgers", "Interpreting economic indicators", "Business case study analysis"),
      furtherStudy = listOf("B.Com (General or Hons)", "BBA / BMS", "Integrated B.A. LL.B", "ICAI CA Foundation registration"),
      questionsToAsk = listOf(
        "Is Applied Mathematics or standard Mathematics available as an elective?",
        "Does the commerce department host commerce clubs or mock stock-market workshops?"
      ),
      whatToVerify = listOf("Maths requirements for specific elite universities' B.Com Honours admissions.")
    ),
    Course(
      id = "crs-inter-hec",
      title = "Intermediate / Class 11-12: Humanities & Social Sciences",
      area = "Secondary Schooling",
      stage = StudyStage.AFTER_10TH,
      courseType = "Academic Higher Secondary",
      approxDuration = "2 years (Classes 11 & 12)",
      subjectsOrSkills = listOf("History", "Political Science", "Psychology or Sociology", "Economics", "Literature"),
      relatedCareers = listOf("Civil Services Officer", "Journalist", "Clinical Psychologist", "Advocate", "Urban Policy Planner"),
      personalizationNote = "Nurtures deep critical inquiry, historical perspective, and nuanced written communication.",
      whatStudentsStudy = listOf(
        "Indian and world history, social movements, and archaeological excavations",
        "Constitutional frameworks, democratic rights, and international relations",
        "Human psychological development, cognitive processes, and social behavior"
      ),
      skillsPractised = listOf("Essay drafting", "Source criticism and fact-checking", "Debating ethical dilemmas"),
      furtherStudy = listOf("B.A. (Hons) in Psychology / Political Science / Economics", "5-year Law (B.A. LL.B)", "B.Des in Design"),
      questionsToAsk = listOf(
        "What specific combination of 4 core humanities electives is offered in this institution?",
        "Does the school encourage external project work and essay competitions?"
      ),
      whatToVerify = listOf("Availability of qualified teachers for specialized subjects like Psychology or Sociology.")
    ),
    Course(
      id = "crs-btech-cse",
      title = "B.Tech in Computer Science & Engineering",
      area = "Engineering & Technology",
      stage = StudyStage.AFTER_12TH,
      courseType = "Professional Degree",
      approxDuration = "4 years (8 semesters)",
      subjectsOrSkills = listOf("Algorithms & data structures", "Operating systems", "Database design", "Computer networks", "Software engineering"),
      relatedCareers = listOf("Software Engineer", "Systems Architect", "Data Engineer", "Cloud Specialist"),
      personalizationNote = "For students who want to build software systems, apps, and computer infrastructure.",
      whatStudentsStudy = listOf(
        "Discrete mathematics, graph theory, and computational complexity",
        "Object-oriented programming in Java/C++, web development, and cloud computing",
        "Operating systems, compilers, computer organization, and distributed databases"
      ),
      skillsPractised = listOf("Coding complex algorithms", "Collaborative Git development", "Debugging software", "Capstone team project"),
      furtherStudy = listOf("M.Tech / M.S. in Computer Science", "MBA in Technology Management", "Direct research fellowships"),
      questionsToAsk = listOf(
        "What is the average student-to-computer ratio in laboratory sessions?",
        "Are open-source internships and hackathons actively encouraged?"
      ),
      whatToVerify = listOf("NBA accreditation of the specific CSE department and university UGC recognition.")
    ),
    Course(
      id = "crs-bsc-nursing",
      title = "B.Sc in Nursing (Basic)",
      area = "Healthcare & Life Sciences",
      stage = StudyStage.AFTER_12TH,
      courseType = "Professional Degree",
      approxDuration = "4 years (including compulsory clinical internship)",
      subjectsOrSkills = listOf("Human anatomy & physiology", "Medical-surgical nursing", "Pharmacology", "Pediatric care", "Community health"),
      relatedCareers = listOf("Registered Clinical Nurse", "Critical Care Specialist", "Community Health Nurse", "Nursing Educator"),
      personalizationNote = "A vital healthcare vocation requiring clinical discipline, deep empathy, and steady composure under pressure.",
      whatStudentsStudy = listOf(
        "Bedside nursing procedures, aseptic techniques, and medication safety",
        "Maternal, neonatal, and mental health nursing practice",
        "Hospital management, healthcare ethics, and epidemiological disease tracking"
      ),
      skillsPractised = listOf("Patient vital monitoring", "Intravenous administration", "Emergency triage assistance", "Compassionate patient education"),
      furtherStudy = listOf("M.Sc Nursing in specialized clinical streams (Cardiology, Oncology, Pediatrics)", "Healthcare Administration"),
      questionsToAsk = listOf(
        "How many bedded parent hospital is attached to the nursing college?",
        "What is the clinical duty schedule during the 3rd and 4th years?"
      ),
      whatToVerify = listOf("Recognition by the Indian Nursing Council (INC) and State Nursing Council.")
    ),
    Course(
      id = "crs-bsc-agri",
      title = "B.Sc (Honours) in Agriculture",
      area = "Agriculture & Environment",
      stage = StudyStage.AFTER_12TH,
      courseType = "Applied Professional Degree",
      approxDuration = "4 years (including RAWE rural attachment)",
      subjectsOrSkills = listOf("Agronomy & crop production", "Soil science & soil chemistry", "Genetics & plant breeding", "Plant pathology", "Agri-economics"),
      relatedCareers = listOf("Agricultural Extension Officer", "Seed Production Specialist", "Agri-business Manager", "Soil Health Analyst"),
      personalizationNote = "Combines laboratory science with extensive field trials across seasons.",
      whatStudentsStudy = listOf(
        "Principles of organic farming, integrated pest management, and weed science",
        "Farm machinery, tractor operation, micro-irrigation, and post-harvest technology",
        "Rural Agricultural Work Experience (RAWE) living in rural farming communities"
      ),
      skillsPractised = listOf("Soil testing & fertilizer calculation", "Crop disease diagnosis", "Field trial documentation"),
      furtherStudy = listOf("M.Sc (Agri) via ICAR JRF entrance", "MBA in Agribusiness Management (IABM / IIM-A FABM)"),
      questionsToAsk = listOf(
        "Is the university ICAR accredited (crucial for central government agricultural exam eligibility)?",
        "How many acres of agricultural research farm are maintained for student practicals?"
      ),
      whatToVerify = listOf("ICAR accreditation status of the college on icar.org.in.")
    ),
    Course(
      id = "crs-bcom-hons",
      title = "Bachelor of Commerce (Honours)",
      area = "Commerce, Accounting & Finance",
      stage = StudyStage.AFTER_12TH,
      courseType = "University Undergraduate Degree",
      approxDuration = "3 to 4 years (as per NEP 2020 framework)",
      subjectsOrSkills = listOf("Corporate accounting", "Income tax law", "Financial management", "Cost accounting", "Auditing standards"),
      relatedCareers = listOf("Chartered Accountant", "Financial Analyst", "Tax Consultant", "Credit Manager"),
      personalizationNote = "Provides a deep, rigorous grounding in financial reporting and corporate finance laws.",
      whatStudentsStudy = listOf(
        "Advanced corporate accounting standards (Ind AS / IFRS)",
        "Financial market instruments, portfolio theory, and valuation methods",
        "Indian Companies Act 2013 and Goods & Services Tax (GST) mechanisms"
      ),
      skillsPractised = listOf("Financial statement modeling", "Tax computation schedules", "Audit sampling techniques"),
      furtherStudy = listOf("M.Com / MBA Finance", "Professional qualifications (CA / CFA / ACCA / CS)"),
      questionsToAsk = listOf(
        "Does the college offer exemptions or coaching support for professional certifications (CA/ACCA)?",
        "What corporate banking and audit firms participate in campus placements?"
      ),
      whatToVerify = listOf("University UGC recognition and NAAC grading.")
    ),
    Course(
      id = "crs-ballb-integrated",
      title = "5-Year Integrated B.A. LL.B (Honours)",
      area = "Law & Legal Studies",
      stage = StudyStage.AFTER_12TH,
      courseType = "Integrated Professional Law Degree",
      approxDuration = "5 years (10 semesters)",
      subjectsOrSkills = listOf("Constitutional law", "Law of crimes (IPC/BNS)", "Law of contracts", "Civil procedure code", "Human rights & legal aid"),
      relatedCareers = listOf("Litigation Advocate", "Corporate Legal Associate", "Judicial Magistrate", "Legal Compliance Officer"),
      personalizationNote = "Integrates humanities foundations (political science, sociology) seamlessly with five years of legal scholarship.",
      whatStudentsStudy = listOf(
        "Jurisprudence, constitutional interpretation, criminal law, and evidence law",
        "Corporate governance, intellectual property rights, environmental law, and cyber law",
        "Clinical legal education: drafting pleadings, conveyancing, and trial advocacy"
      ),
      skillsPractised = listOf("Moot court oral advocacy", "Legal research using case databases", "Contract drafting"),
      furtherStudy = listOf("LL.M in International Law / Corporate Law", "State Judicial Services Examination"),
      questionsToAsk = listOf(
        "Is the law faculty approved by the Bar Council of India (BCI)?",
        "How active is the student moot court society and legal aid clinic?"
      ),
      whatToVerify = listOf("Bar Council of India (BCI) affiliation notification.")
    ),
    Course(
      id = "crs-bdes-product",
      title = "Bachelor of Design (B.Des) in Industrial / UI Design",
      area = "Design & Creative Fields",
      stage = StudyStage.AFTER_12TH,
      courseType = "Professional Design Degree",
      approxDuration = "4 years (8 semesters)",
      subjectsOrSkills = listOf("Design thinking & empathy", "Form studies & color theory", "Digital wireframing", "Material prototyping", "User research"),
      relatedCareers = listOf("UI/UX Designer", "Product Designer", "Design Researcher", "Design Consultant"),
      personalizationNote = "Ideal for students bridging creative aesthetics, human psychology, and functional technology.",
      whatStudentsStudy = listOf(
        "Elements of visual design, ergonomics, human factor engineering, and semantics",
        "User journey mapping, cognitive walkthroughs, and accessibility compliance",
        "Physical model making with clay, foam, 3D printing, and digital CAD modeling"
      ),
      skillsPractised = listOf("User observation interviews", "Figma / CAD tool mastery", "Portfolio critique presentations"),
      furtherStudy = listOf("M.Des (Master of Design via CEED / NID)", "Interaction Design specializations"),
      questionsToAsk = listOf(
        "Are studio spaces open 24/7 with prototyping machinery and materials?",
        "Does the curriculum include an industry-sponsored graduation project semester?"
      ),
      whatToVerify = listOf("Admission via national entrance (UCEED, NID DAT, or institutional portfolio review).")
    ),
    Course(
      id = "crs-bvoc-hospitality",
      title = "B.Voc in Hospitality & Hotel Administration",
      area = "Hospitality & Tourism",
      stage = StudyStage.AFTER_12TH,
      courseType = "Work-Integrated Bachelor (B.Voc)",
      approxDuration = "3 years (with multiple exit diploma certificates)",
      subjectsOrSkills = listOf("Food & beverage service", "Front office operations", "Culinary fundamentals", "Hospitality accounting", "Guest relations"),
      relatedCareers = listOf("Hotel Operations Executive", "Event Planner", "Customer Experience Manager", "Catering Supervisor"),
      personalizationNote = "A dynamic, people-oriented industry with 60% practical training built into the degree.",
      whatStudentsStudy = listOf(
        "International culinary arts, hygiene standards (HACCP), and kitchen safety",
        "Property management software, room reservations, and front desk etiquette",
        "Tourism trends, banquet planning, and wine appreciation"
      ),
      skillsPractised = listOf("Table service coordination", "Guest conflict resolution", "Event banquet budgeting"),
      furtherStudy = listOf("Master of Tourism and Travel Management (MTTM)", "Corporate Hospitality Management"),
      questionsToAsk = listOf(
        "Which star-rated hotels are affiliated for mandatory semester internships?",
        "Is the training certified under the National Skill Qualification Framework (NSQF) Level 7?"
      ),
      whatToVerify = listOf("UGC approval for the B.Voc scheme and NCHMCT / AICTE affiliation.")
    ),
    Course(
      id = "crs-dpharm",
      title = "Diploma in Pharmacy (D.Pharm)",
      area = "Healthcare & Life Sciences",
      stage = StudyStage.AFTER_12TH,
      courseType = "Professional Paramedical Diploma",
      approxDuration = "2 years + 500 hours hospital training",
      subjectsOrSkills = listOf("Pharmaceutics", "Pharmaceutical chemistry", "Pharmacology", "Hospital & clinical pharmacy", "Pharmacy laws & ethics"),
      relatedCareers = listOf("Registered Community Pharmacist", "Hospital Pharmacy Dispenser", "Medical Sales Specialist"),
      personalizationNote = "Enables registration as a licensed pharmacist to operate pharmacies or dispense hospital medicines.",
      whatStudentsStudy = listOf(
        "Drug formulation, dosage calculations, and packaging of pharmaceuticals",
        "Action of medicines on human organ systems and adverse drug interactions",
        "Drugs and Cosmetics Act, drug storage temperatures, and prescription dispensing"
      ),
      skillsPractised = listOf("Safe compounding of solutions", "Prescription verification", "Patient medication counseling"),
      furtherStudy = listOf("Lateral entry into 2nd year B.Pharm (Bachelor of Pharmacy)", "Specialized clinical trials training"),
      questionsToAsk = listOf(
        "Is the institute approved by the Pharmacy Council of India (PCI) under Section 12?",
        "Does the college facilitate the mandatory 500-hour government hospital dispensing internship?"
      ),
      whatToVerify = listOf("Pharmacy Council of India (PCI) valid approval list for the current academic session.")
    ),
    Course(
      id = "crs-itep-teacher",
      title = "Integrated Teacher Education Programme (ITEP: B.A. B.Ed / B.Sc. B.Ed)",
      area = "Education & Teacher Training",
      stage = StudyStage.AFTER_12TH,
      courseType = "Integrated Professional Degree",
      approxDuration = "4 years (8 semesters)",
      subjectsOrSkills = listOf("Child psychology & learning", "Curriculum design", "Pedagogy of school subjects", "Inclusive education", "School internship"),
      relatedCareers = listOf("Secondary School Teacher", "Educational Content Developer", "Curriculum Designer"),
      personalizationNote = "A unified 4-year degree created under NEP 2020 to train modern, empathetic educators right after Class 12.",
      whatStudentsStudy = listOf(
        "Cognitive development in adolescents and learning theories",
        "Pedagogy of chosen school subjects (Mathematics, Science, Social Sciences, or Languages)",
        "Classroom management, continuous assessment, and special needs education"
      ),
      skillsPractised = listOf("Micro-teaching simulations", "Formative assessment design", "School internship teaching blocks"),
      furtherStudy = listOf("M.Ed (Master of Education)", "M.A. in chosen academic discipline", "Teacher Eligibility Test (TET/CTET) qualifications"),
      questionsToAsk = listOf(
        "Is the programme approved by the National Council for Teacher Education (NCTE)?",
        "What local schools are partnered for the prolonged final-year teaching residency?"
      ),
      whatToVerify = listOf("National Common Entrance Test (NCET) conducted by NTA for central and state universities.")
    ),
    Course(
      id = "crs-nios-senior-sec",
      title = "Open Schooling Senior Secondary (Class 12 equivalent)",
      area = "Flexible & Open Schooling",
      stage = StudyStage.AFTER_10TH,
      courseType = "Open School Certificate",
      approxDuration = "Flexible (up to 5 years with 9 exam attempts)",
      subjectsOrSkills = listOf("Choose any 5 subjects from broad academic & vocational pools", "Self-study modular books", "Tutor Marked Assignments (TMA)"),
      relatedCareers = listOf("Flexible Higher Studies", "Sports / Creative Careers", "Government Examination Aspirants"),
      personalizationNote = "Recognized by AIU and government bodies, ideal for learners balancing arts, sports, or family commitments.",
      whatStudentsStudy = listOf(
        "Flexible subject combinations without rigid stream boundaries (e.g. History + Biology + Painting + Data Entry + Hindi)",
        "Printed comprehensive modules and audio-video learning aids on Swayam portal",
        "Practical laboratory training at designated accredited institutions (AI centres)"
      ),
      skillsPractised = listOf("Independent self-scheduled study", "Digital assignment submission", "Practical skill assignments"),
      furtherStudy = listOf("Any university degree, CUET entrance, NEET, JEE, or state civil examinations"),
      questionsToAsk = listOf(
        "Is your local study centre equipped for the mandatory Personal Contact Programmes (PCP)?",
        "How do On-Demand Examinations (ODE) work if you need results earlier?"
      ),
      whatToVerify = listOf("Accreditation status of the study centre on nios.ac.in.")
    )
  )

  val sampleChatSuggestions = listOf(
    "I enjoy solving problems",
    "I like helping people",
    "I'm drawn to art or design",
    "I enjoy nature and the environment",
    "I'm interested in business",
    "I'm still figuring it out"
  )
}
